package applicationa.demo.pegawai.controller;

import applicationa.demo.feign.data.PegawaiData;
import applicationa.demo.pegawai.form.PegawaiForm;
import applicationa.demo.pegawai.service.PegawaiSvc;
import applicationa.demo.util.DataNotFoundException;
import applicationa.demo.util.ValidationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pegawai")
public class PegawaiController {

	private final PegawaiSvc pegawaiSvc;
	
	@Autowired
	public PegawaiController(PegawaiSvc pegawaiSvc){
		this.pegawaiSvc = pegawaiSvc;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public PegawaiData createNew(@Valid @RequestBody PegawaiForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationErrorException(bindingResult.getAllErrors());
		}
		PegawaiData data = pegawaiSvc.postDataPegawai(form);
		if (data == null){
			throw new DataNotFoundException("gagal menyimpan");
		}
		return data;
	}
	
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	@ResponseBody
	public PegawaiData getDetail(@RequestParam(name = "id") Long id) {
		PegawaiData pegawaiData = pegawaiSvc.getById(id);
		if (pegawaiData == null) {
			throw new DataNotFoundException("data pegawai tidak ditemukan");
		}
		return pegawaiData;
	}
}
