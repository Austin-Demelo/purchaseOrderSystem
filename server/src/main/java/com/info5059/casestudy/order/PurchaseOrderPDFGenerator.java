package com.info5059.casestudy.order;

import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.vendor.Vendor;
import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.product.ProductRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PurchaseOrderPDFGenerator extends AbstractPdfView {
    public static ByteArrayInputStream generatePurchaseOrder(String poid,
                                                             PurchaseOrderDAO poDAO,
                                                             VendorRepository vendorRepository,
                                                             ProductRepository productRepository) throws IOException{
        URL imageUrl = PurchaseOrderPDFGenerator.class.getResource("/static/assets/images/LastCallMafiaLogo.png");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        // Initialize PDF document to be written to a stream not a file
        PdfDocument pdf = new PdfDocument(writer);
        // Document is the main object
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        // add the image to the document
        Image img = new Image(ImageDataFactory.create(imageUrl))
                .scaleAbsolute(128, 128)
                .setFixedPosition(80, 710);
        document.add(img);
        document.add(new Paragraph("\n\n"));
        Locale locale = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

        try {
            PurchaseOrder po = poDAO.findOne(Long.parseLong(poid));

            document.add(new Paragraph(String.format("Purchase Order"))
                    .setFont(font)
                    .setFontSize(24)
                    .setMarginRight(75)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBold());
            document.add(new Paragraph("#:" + poid)
                    .setFont(font)
                    .setFontSize(16)
                    .setBold()
                    .setMarginRight(145)
                    .setMarginTop(-10)
                    .setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n\n"));

            Optional<Vendor> opt = vendorRepository.findById(po.getVendorid());
            if (opt.isPresent()) {
                Vendor vendor = opt.get();

                // now a 1 column table
                Table table = new Table(2);
                table.setWidth(new UnitValue(UnitValue.PERCENT, 35));
                // Unfortunately we must format each cell individually :(
                // table headings
                Cell cell = new Cell().add(new Paragraph("Vendor:")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.LEFT);
                table.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getName())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.LEFT);
                table.addCell(cell);
                // table details
                table.addCell(new Cell().setBorder(Border.NO_BORDER));
                cell = new Cell().add(new Paragraph(vendor.getAddress1())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.LEFT);
                table.addCell(cell);
                table.addCell(new Cell().setBorder(Border.NO_BORDER));
                cell = new Cell().add(new Paragraph(vendor.getCity())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.LEFT);
                table.addCell(cell);
                table.addCell(new Cell().setBorder(Border.NO_BORDER));
                cell = new Cell().add(new Paragraph(vendor.getProvince())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.LEFT);
                table.addCell(cell);
                table.addCell(new Cell().setBorder(Border.NO_BORDER));
                cell = new Cell().add(new Paragraph(vendor.getEmail())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.LEFT);
                table.addCell(cell);
                document.add(table);

                // add table spacing
                document.add(new Paragraph("\n\n"));

                // now a 3 column table
                Table productTable = new Table(5);
                productTable.setWidth(new UnitValue(UnitValue.PERCENT, 100));
                // table headings
                cell = new Cell().add(new Paragraph("Product Code")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                productTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Description")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                productTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Qty Sold")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                productTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Price")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                productTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Ext. Price")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                productTable.addCell(cell);

                BigDecimal tot = new BigDecimal(0.0);

                for (PurchaseOrderLineitem line : po.getItems()) {
                    Optional<Product> optx = productRepository.findById(line.getProductid());
                    if (optx.isPresent()) {
                        Product prod = optx.get();

                        // table details
                        cell = new Cell().add(new Paragraph(prod.getId())
                                .setFont(font)
                                .setFontSize(12)
                                .setTextAlignment(TextAlignment.CENTER));
                        productTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(prod.getName())
                                .setFont(font)
                                .setFontSize(12)
                                .setTextAlignment(TextAlignment.CENTER));
                        productTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(line.getQty() + "") // Java compiler casts int to string
                                .setFont(font)
                                .setFontSize(12)
                                .setTextAlignment(TextAlignment.RIGHT));
                        productTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(formatter.format(prod.getCostprice()))
                                .setFont(font)
                                .setFontSize(12)
                                .setTextAlignment(TextAlignment.RIGHT));
                        productTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(formatter.format(line.getPrice().multiply(new BigDecimal(line.getQty()))))
                                .setFont(font)
                                .setFontSize(12)
                                .setTextAlignment(TextAlignment.RIGHT));
                        productTable.addCell(cell);

                        tot = tot.add(line.getPrice().multiply(new BigDecimal(line.getQty())), new MathContext(8, RoundingMode.UP));
                    }
                }

                // sub total
                cell = new Cell(1, 4).add(new Paragraph("Sub Total:"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT.RIGHT);
                productTable.addCell(cell);
                cell = new Cell().add(new Paragraph(formatter.format(tot)))
                        .setTextAlignment(TextAlignment.RIGHT.RIGHT);
                productTable.addCell(cell);
                // tax
                cell = new Cell(1, 4).add(new Paragraph("Tax:"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT.RIGHT);
                productTable.addCell(cell);
                cell = new Cell().add(new Paragraph(formatter.format(tot.multiply(new BigDecimal(0.13)))))
                        .setTextAlignment(TextAlignment.RIGHT.RIGHT);
                productTable.addCell(cell);
                // pruchase order total
                cell = new Cell(1, 4).add(new Paragraph("PO Total:"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT.RIGHT);
                productTable.addCell(cell);
                cell = new Cell().add(new Paragraph(formatter.format(tot.multiply(new BigDecimal(1.13)))))
                        .setTextAlignment(TextAlignment.RIGHT.RIGHT)
                        .setBackgroundColor(ColorConstants.YELLOW);
                productTable.addCell(cell);

                document.add(productTable);

                document.add(new Paragraph("\n\n"));
                document.add(new Paragraph(String.valueOf(new Date()))
                        .setTextAlignment(TextAlignment.CENTER));

            }

            document.close();
        } catch (Exception ex) {
            Logger.getLogger(PurchaseOrderPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(baos.toByteArray());
    }
}
