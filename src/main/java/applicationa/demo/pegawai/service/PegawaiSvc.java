package applicationa.demo.pegawai.service;

import applicationa.demo.feign.data.PegawaiData;
import applicationa.demo.pegawai.form.PegawaiForm;


public interface PegawaiSvc {
	
	PegawaiData getById(Long id);
	
	PegawaiData postDataPegawai(PegawaiForm form);
	
}
