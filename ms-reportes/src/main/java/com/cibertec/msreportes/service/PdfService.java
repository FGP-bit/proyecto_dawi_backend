package com.cibertec.msreportes.service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.cibertec.gestionmedica.entity.Cita;
import com.cibertec.msreportes.repository.CitaRepository;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {

	@Autowired
	private CitaRepository citaRepository;

	private static final Color COLOR_PRIMARIO = new Color(0, 102, 204); // Azul corporativo
	private static final Color COLOR_FONDO_HEADER = new Color(230, 240, 255); // Azul muy claro
	private static final Color COLOR_ALTERNADO = new Color(245, 245, 245); // Gris claro para filas


	private static final Font FONT_TITULO_CLINICA = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, COLOR_PRIMARIO);
	private static final Font FONT_INFO_CLINICA = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
	private static final Font FONT_TITULO_REPORTE = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.BLACK);
	private static final Font FONT_HEADER_TABLA = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.WHITE);
	private static final Font FONT_CELDA_TABLA = FontFactory.getFont(FontFactory.HELVETICA, 9, Color.BLACK);

	public ByteArrayInputStream generarReporteCitasPdf() {
		Document document = new Document(PageSize.A4, 36, 36, 36, 36); 
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();

			
			agregarCabeceraClinica(document);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);


			Paragraph tituloReporte = new Paragraph("Reporte Detallado de Citas Médicas", FONT_TITULO_REPORTE);
			tituloReporte.setAlignment(Element.ALIGN_CENTER);
			document.add(tituloReporte);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			Paragraph fechaGen = new Paragraph("Generado el: " + LocalDateTime.now().format(formatter), FONT_INFO_CLINICA);
			fechaGen.setAlignment(Element.ALIGN_CENTER);
			document.add(fechaGen);
			
			document.add(Chunk.NEWLINE);

		
			PdfPTable table = crearTablaCitas();
			document.add(table);

		
			document.add(Chunk.NEWLINE);
			Paragraph footer = new Paragraph("Este documento es un reporte confidencial de la Clínica Santa Lucía. Documento generado electrónicamente.", FONT_INFO_CLINICA);
			footer.setAlignment(Element.ALIGN_CENTER);
			document.add(footer);
			
			document.close();

		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}



	private void agregarCabeceraClinica(Document document) throws DocumentException, IOException {

		PdfPTable headerTable = new PdfPTable(2);
		headerTable.setWidthPercentage(100);
		headerTable.setWidths(new int[] { 1, 3 }); 


		PdfPCell cellLogo = new PdfPCell();
		cellLogo.setBorder(PdfPCell.NO_BORDER);
		cellLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		try {
			
			ClassPathResource imgFile = new ClassPathResource("static/images/logo-clinica.png");
			Image img = Image.getInstance(imgFile.getURL());
			img.scaleToFit(120, 80); 
			cellLogo.addElement(img);
		} catch (Exception e) {
			
			cellLogo.addElement(new Paragraph("CLÍNICA MEDICAL CARE", FONT_TITULO_CLINICA));
		}
		headerTable.addCell(cellLogo);

		
		PdfPCell cellInfo = new PdfPCell();
		cellInfo.setBorder(PdfPCell.NO_BORDER);
		cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellInfo.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Paragraph pNombre = new Paragraph("CLÍNICA MEDICAL CARE", FONT_TITULO_CLINICA);
		pNombre.setAlignment(Element.ALIGN_RIGHT);
		cellInfo.addElement(pNombre);

		Paragraph pDireccion = new Paragraph("Av. Brena 123, Lima - Perú\nTel: (01) 555-0102\ninfo@clinicamedicalcare.com", FONT_INFO_CLINICA);
		pDireccion.setAlignment(Element.ALIGN_RIGHT);
		cellInfo.addElement(pDireccion);

		headerTable.addCell(cellInfo);

		document.add(headerTable);
		
		
		PdfPTable separator = new PdfPTable(1);
		separator.setWidthPercentage(100);
		PdfPCell lineCell = new PdfPCell(new Phrase(" "));
		lineCell.setBackgroundColor(COLOR_PRIMARIO);
		lineCell.setBorder(PdfPCell.NO_BORDER);
		lineCell.setFixedHeight(3f); 
		separator.addCell(lineCell);
		document.add(separator);
	}

	private PdfPTable crearTablaCitas() throws DocumentException {
		PdfPTable table = new PdfPTable(5); 
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setWidths(new float[] { 0.8f, 2f, 2.5f, 2.5f, 1.5f }); 

		
		String[] headers = { "ID", "Fecha/Hora", "Paciente", "Médico", "Estado" };
		for (String header : headers) {
			PdfPCell hCell = new PdfPCell(new Phrase(header.toUpperCase(), FONT_HEADER_TABLA));
			hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			hCell.setBackgroundColor(COLOR_PRIMARIO); 
			hCell.setPaddingTop(8f);
			hCell.setPaddingBottom(8f);
			table.addCell(hCell);
		}

		
		List<Cita> citas = citaRepository.findAll();
		boolean alternate = false; 

		for (Cita cita : citas) {
			addDatoCell(table, cita.getId().toString(), alternate);
			addDatoCell(table, cita.getFecha() + "\n" + cita.getHora(), alternate);
			addDatoCell(table, cita.getPaciente().getNombre(), alternate);
			addDatoCell(table, cita.getMedico().getNombre(), alternate);
			addDatoCell(table, cita.getEstado(), alternate);
			
			alternate = !alternate; 
		}

		return table;
	}

	
	private void addDatoCell(PdfPTable table, String text, boolean alternate) {
		PdfPCell cell = new PdfPCell(new Phrase(text != null ? text : "", FONT_CELDA_TABLA));
		cell.setPadding(6f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		if (alternate) {
			cell.setBackgroundColor(COLOR_ALTERNADO); 
		}
		
		
		if(text.length() < 5 || text.equals("PENDIENTE") || text.equals("ATENDIDO")) {
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		table.addCell(cell);
	}
}