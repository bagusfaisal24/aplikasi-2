package applicationa.demo.feign.client;

import applicationa.demo.feign.data.PegawaiData;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PegawaiClient {
	
	@RequestLine("GET /v1/pegawai/findById?id={id}")
	PegawaiData getPegawaiById(@Param("id") Long id);
	
	@RequestLine("GET /v1/pegawai")
	@Headers("Content-Type: application/json")
	@Body("%7B\"name\": \"{name}\", \"alamat\": \"{alamat}\"%7D")
	PegawaiData postData(@Param("name") String name, @Param("alamat") String alamat);
}
