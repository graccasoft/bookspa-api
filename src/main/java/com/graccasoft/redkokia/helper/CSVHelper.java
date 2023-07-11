package com.graccasoft.redkokia.helper;

import com.graccasoft.redkokia.model.dto.BookingReportDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
public class CSVHelper {

    public static ByteArrayInputStream bookingsToCsv(List<BookingReportDto> bookings){
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (BookingReportDto booking : bookings) {
                List<String> data = Arrays.asList(
                        String.valueOf( booking.getBookingDate() ),
                        booking.getTreatments(),
                        booking.getEmployee(),
                        booking.getClient(),
                        booking.getStatus().toString(),
                        booking.getPaymentMethod()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
