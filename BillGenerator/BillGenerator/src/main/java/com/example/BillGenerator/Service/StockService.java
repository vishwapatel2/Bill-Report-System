package com.example.BillGenerator.Service;

import com.example.BillGenerator.Model.Product;
import com.example.BillGenerator.Repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Component
public class StockService {
    @Autowired
    ProductRepository productRepository;
    @Scheduled(cron = " 59 41 10 * * *")
    public void generateStockReport(){
        List<Product> products=productRepository.findAll();
        Date date=new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        String fileName="stock_report_"+simpleDateFormat.format(date);
        System.out.println(fileName);
        try (
                //writer class is autocloseable so need to close resource
                Writer writer = new FileWriter("C:\\Users\\Mayur\\Desktop\\SpringBoot myProject\\BillGenerator\\BillGenerator\\src\\main\\resources\\reports\\"+fileName);
                ICsvBeanWriter iCsvBeanWriter=new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        ) {
            String[] headers={"pId","pName","mrp","gst","stockQuantity"};
            iCsvBeanWriter.writeHeader(headers);
            for (Product product:products){
                iCsvBeanWriter.write(product,headers);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
