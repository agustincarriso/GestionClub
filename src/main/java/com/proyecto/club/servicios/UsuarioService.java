package com.proyecto.club.servicios;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Foto;
import com.proyecto.club.entidades.Usuario;
import com.proyecto.club.entidades.Socio;
import com.proyecto.club.enums.Role;
import com.proyecto.club.repositorios.SocioRepository;
import com.proyecto.club.repositorios.UsuarioRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SocioRepository socioRepository;

	@Autowired
	private FotoService fotoService;

	@Autowired
	public MailService mailService;

	@Transactional
	public Usuario save(Usuario usuario, MultipartFile archivo) throws WebException, IOException {

		String[] symbols = {"+", "=", "-", "*", "'"};

		if (usuario.getNombre().isEmpty() || usuario.getNombre() == null) {

			throw new WebException("El nombre no puede estar vacio");
		}

		if (usuario.getApellido().isEmpty() || usuario.getApellido() == null) {

			throw new WebException("El apellido no puede estar vacio");
		}

		if (usuario.getDomicilio().isEmpty() || usuario.getDomicilio() == null) {

			throw new WebException("El domicilio no puede estar vacio");
		}

		if (usuario.getEmail().isEmpty() || usuario.getEmail() == null) {

			throw new WebException("El email no puede estar vacio");
		}

		Usuario user = findByEmail(usuario.getEmail());
		if (user != null) {
			throw new WebException("El email ya está registrado");
		}

		if (usuario.getPassword().isEmpty() || usuario.getPassword() == null) {

			throw new WebException("El password no puede estar vacio");
		}
		if (usuario.getPassword().length() < 6) {
			throw new WebException("La contraseña debe tener al menos 6 caracteres");
		}

		for (int i = 0; i < symbols.length; i++) {
			if (usuario.getPassword().contains(symbols[i])) {
				throw new WebException("La contraseña no debe contener símbolos");
			}
		}

		Usuario usuario2 = usuarioRepository.findByDni(usuario.getDni());

		if (usuario.getDni().isEmpty() || usuario.getDni() == null) {

			throw new WebException("El DNI no puede ser nulo");
		}
		if (usuario2 != null) {

			throw new WebException("No se puede crear un usuario con un DNI existente");
		}
		if (usuario.getDni().length() != 8) {
			throw new WebException("Formato de DNI incorrecto");
		}

		if (usuario.getTelefono().isEmpty() || usuario.getTelefono() == null) {

			throw new WebException("El telefono no puede estar vacio");
		}

		Foto img = fotoService.guardarFoto(archivo);

		usuario.setFoto(img);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setPassword(encoder.encode(usuario.getPassword()));

		usuario.setRol(Role.USER);

		mailService.enviarMail("Bienvenido al Club!", "Club Talleres", usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> listAll() {
		List<Usuario> lista = usuarioRepository.findAll();
		return lista;
	}

	//cree este servicio (jonathan)
	public List<Usuario> listAllByQ(String query) {
		List<Usuario> lista = usuarioRepository.findAllByQ("%" + query + "%");
		return lista;
	}

	public Optional<Usuario> findById(String id) {

		return usuarioRepository.findById(id);
	}

	public Usuario encontrarPorId(String id) {
		return usuarioRepository.encontrarPorId(id);
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public Usuario findByDni(String dni) throws WebException {
		return usuarioRepository.findByDni(dni);
	}

	@Transactional
	public void deleteById(String id) throws WebException {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.delete(optional.get());

		} else {
			throw new WebException("No se encontra la usuario seleccionada");
		}

	}

	@Transactional
	public void deleteByObject(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Transactional
	public Socio cambioASocio(Usuario usuario, MultipartFile archivo, Double valorCuota) throws IOException, WebException {

		Foto img = fotoService.guardarFoto(archivo);

		Socio socio = new Socio();
		socio.setId(usuario.getId());
		socio.setNombre(usuario.getNombre());
		socio.setApellido(usuario.getApellido());
		socio.setDni(usuario.getDni());
		socio.setEmail(usuario.getEmail());
		socio.setDomicilio(usuario.getDomicilio());
		socio.setFechaInicio(new Date());
		socio.setPassword(usuario.getPassword());
		socio.setFoto(img);
		socio.setRol(Role.SOCIO);
		socio.setTelefono(usuario.getTelefono());
		socio.setValorCuota(valorCuota);
		socio.setNumeroSocio(UsuarioService.this.listAll().size() + 1);
		deleteById(usuario.getId());
		if (socio.getValorCuota()==500) {
			socio.setAcceso("Popular");
		}
		if (socio.getValorCuota()==1000) {
			socio.setAcceso("Platea descubierta");
		}
		if (socio.getValorCuota()==1500) {
			socio.setAcceso("Platea cubierta");
		}
		if (socio.getValorCuota()==2000) {
			socio.setAcceso("Palco");
		}
		return socioRepository.save(socio);

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Usuario usuario = usuarioRepository.findByEmail(email);
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
			if (usuario.getRol().equals(Role.ADMIN)) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				authorities.add(new SimpleGrantedAuthority("ROLE_SOCIO"));
			}
			if (usuario.getRol().equals(Role.SOCIO)) {
				authorities.add(new SimpleGrantedAuthority("ROLE_SOCIO"));
			}
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usuariosession", usuario);
			return new User(email, usuario.getPassword(), authorities);
		} catch (Exception e) {
			throw new UsernameNotFoundException("El usuario no existe");
		}
	}
}
