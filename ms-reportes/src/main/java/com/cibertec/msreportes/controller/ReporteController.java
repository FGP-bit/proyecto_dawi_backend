package com.cibertec.msreportes.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.msreportes.service.PdfService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

	@Autowired
	private PdfService pdfService;

	@GetMapping("/citas-pdf")
	public ResponseEntity<InputStreamResource> descargarPdf() {
		ByteArrayInputStream in = pdfService.generarReporteCitasPdf();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=reporte_citas.pdf");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(in));
	}
}