package modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportarPdf {

	public static void gerarPdf(Vector<Relatorio> vetorRel) throws DocumentException, IOException {
		Document doc = null;
		OutputStream os = null;
		int agendados = 0;
		int atendidos = 0;
		int cancelados = 0;
		int espera = 0;
		for (int i = 0; i < vetorRel.size(); i++) {
			Relatorio relatorio = vetorRel.get(i);
			if (relatorio.getStatus().equals("AGENDADO")) {
				agendados++;
			} else if (relatorio.getStatus().equals("ATENDIDO")) {
				atendidos++;
			} else if (relatorio.getStatus().equals("CANCELADO")) {
				cancelados++;
			} else {
				espera++;
			}

		}
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
		String data = formatoData.format(new Date());

		try {

			// cria o documento tamanho A4, margens de 2,54cm
			doc = new Document(PageSize.A4.rotate());

			// cria a stream de saída
			os = new FileOutputStream("c:\\Relatorio "+data+".pdf");
			

			// associa a stream de saída ao
			PdfWriter.getInstance(doc, os);

			// abre o documento
			doc.open();

			//pega o caminho da imagem e colocar a imagem no PDF
			String caminho = System.getProperty("user.dir");			
			Image img = Image.getInstance(caminho+"\\br\\image\\Logo_entalhe_403x132.fw.png");
			img.setAlignment(Element.ALIGN_CENTER);
			doc.add(img);
			
			//Formatando o texto do titulo e colocando ele no PDF
			Font f = new Font(FontFamily.COURIER, 30, Font.BOLD);
			Paragraph titulo = new Paragraph("RELATORIO DE ATENDIMENTOS", f);
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.setSpacingAfter(40);
			titulo.setSpacingBefore(40);
			doc.add(titulo);
			
			// PdfPTable table = new PdfPTable(new float[] { 0.1f, 0.1f, 0.1f,
			// 0.2f, 0.1f, 0.2f, 0.1f, 0.1f });

			PdfPTable table = new PdfPTable(8);
			//PdfPCell header = new PdfPCell(new Paragraph("RELATORIO DE ATENDIMENTOS", f));
			//header.setVerticalAlignment(Element.ALIGN_CENTER);

			//header.setColspan(8);
			//table.addCell(header);

			//preenchendo o arquivo pdf com os atendimentos
			for (int i = 0; i < vetorRel.size(); i++) {
				Relatorio relatorio = vetorRel.get(i);
				table.addCell(relatorio.getData());
				table.addCell(relatorio.getHoraInicio());
				table.addCell(relatorio.getHoraFim());
				table.addCell(relatorio.getFuncionario());
				table.addCell(relatorio.getCliente());
				table.addCell(relatorio.getUnidade());
				table.addCell(relatorio.getServico());
				table.addCell(relatorio.getStatus());
				
			}
			doc.add(table);

			// adiciona o texto ao PDF
			Paragraph p = new Paragraph("AGENDADOS: " + agendados + "\nATENDIDOS: " + atendidos + "\nCANCELADOS: "
					+ cancelados + "\nEM ESPERA: " + espera+ "\nTOTAL: "+vetorRel.size());
			p.setSpacingBefore(20);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);

			JOptionPane.showMessageDialog(null, "Gerado relatório em PDF na pasta C:", "Gordão Barbearia",
					JOptionPane.INFORMATION_MESSAGE);
		} finally {
			if (doc != null) {
				// fechamento do documento
				doc.close();
			}

			if (os != null) {
				// fechamento da stream de saída
				os.close();

			}

		}
	}

	public static void main(String[] args) throws Exception {

	}

}
