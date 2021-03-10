package applicationa.demo.feign.client;

import applicationa.demo.feign.data.PegawaiData;
import applicationa.demo.pegawai.form.PegawaiForm;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PegawaiClientSvc {
	
	@Value("${app.security.uri_backend}")
	@Setter
	private String uri;
	
	public PegawaiData getPegawaiById(Long id) {
		PegawaiClient pegawai = Feign.builder()
				.decoder(new JacksonDecoder())
				.target(PegawaiClient.class, uri);
		return pegawai.getPegawaiById(id);
	}
	
	public PegawaiData postDataPegawai(PegawaiForm form) {
		PegawaiClient pegawai = Feign.builder()
				.decoder(new JacksonDecoder())
				.target(PegawaiClient.class, uri);
		return pegawai.postData(form.getName(), form.getAlamat());
	}
}
