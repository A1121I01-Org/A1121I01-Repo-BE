package module6.backend.service.Impl;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import module6.backend.entity.cart.Cart;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PDFGeneratorServiceImpl {
    public ByteArrayInputStream export(List<Cart> carts) throws IOException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Cart List", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(2);
            Stream.of("Amount","Total Money").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle,headerFont));
                table.addCell(header);
            });

            for (Cart cart: carts) {
                PdfPCell titleCell = new PdfPCell(new Phrase(String.valueOf(cart.getCartQuantity())));
                titleCell.setPaddingLeft(1);
                titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(titleCell);

                PdfPCell desCell = new PdfPCell(new Phrase(String.valueOf(cart.getCartTotalMoney())));
                desCell.setPaddingLeft(1);
                desCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                desCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(desCell);
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
