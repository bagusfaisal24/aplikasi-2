package applicationa.demo.pegawai.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PegawaiForm {
	
	@NotEmpty(message = "tidak boleh kosong")
	private String name;
	
	@NotEmpty(message = "tidak boleh kosong")
	private String alamat;
}
