package applicationa.demo.pegawai.service;

import applicationa.demo.feign.client.PegawaiClientSvc;
import applicationa.demo.feign.data.PegawaiData;
import applicationa.demo.pegawai.form.PegawaiForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PegawaiSvcImpl implements PegawaiSvc {
	
	@Autowired
	private PegawaiClientSvc pegawaiClientSvc;
	
	@Override
	public PegawaiData getById(Long id) {
		try {
			return pegawaiClientSvc.getPegawaiById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public PegawaiData postDataPegawai(PegawaiForm form) {
		try {
			return pegawaiClientSvc.postDataPegawai(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
