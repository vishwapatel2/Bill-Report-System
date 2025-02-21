package com.example.BillGenerator.Service;

import com.example.BillGenerator.Model.Bill;
import com.example.BillGenerator.Model.Customer;
import com.example.BillGenerator.Model.Order;
import com.example.BillGenerator.Model.Product;
import com.example.BillGenerator.Repository.BillRepository;
import com.example.BillGenerator.Repository.CustomerRepository;
import com.example.BillGenerator.Repository.OrderRepository;
import com.example.BillGenerator.Repository.ProductRepository;
import com.example.BillGenerator.configuration.TwilioSmsConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Random;

@Service
public class BillService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BillRepository billRepository;

    public String GenerateBill(Order order)
    {
        Bill bill=new Bill();
        Customer customer=customerRepository.findById(order.getCustomer().getcId()).get();
        Product product=productRepository.findById(order.getProduct().getPid()).get();
        if(product.getStockQuantity()<order.getQuantity() )
        {
            return "stock in not sufficient";
        }
        if(product.getStockQuantity()<50)
        {
            messageForStock(order);
            return "stock in not sufficient";
        }
        bill.setProduct(product);
        bill.setCustomer(customer);
        bill.setOrder(order);
        bill.setGst(product.getGst());
        bill.setMrp(product.getMrp());

        double payableAmount= product.getMrp()+((product.getGst() * product.getMrp())/100);
        bill.setTotalPrice(payableAmount * order.getQuantity());

        if(payment()) {
          orderRepository.save(order);
          billRepository.save(bill);
          product.setStockQuantity(product.getStockQuantity()-order.getQuantity());
          productRepository.save(product);
          messageSucess(order);
          SendMail();
          return "payment successfully";
        }
        else
        {
            messagefail(order);
            return "payment failed";
        }
    }

    public boolean payment()
    {
        Random random=new Random();
        return random.nextBoolean();
    }

    public void messageSucess(Order order)
    {
        Customer customer=customerRepository.findById(order.getCustomer().getcId()).get();
        Twilio.init("ACd400f19e075cfce882e779036328ce0b","2fa5dc456af9cdde28b291681ef203fb");
        Message.creator(new PhoneNumber("+91"+customer.getPhoneNumber()),new PhoneNumber("+19787235258"),"your payment is done.").create();
    }

    public void messagefail(Order order)
    {
        Customer customer=customerRepository.findById(order.getCustomer().getcId()).get();
        Twilio.init("ACd400f19e075cfce882e779036328ce0b","2fa5dc456af9cdde28b291681ef203fb");
        Message.creator(new PhoneNumber("+91"+customer.getPhoneNumber()),new PhoneNumber("+19787235258"),"your payment is failed.").create();
    }

    @Autowired
    private TwilioSmsConfig twilioSmsConfig;
    public void messageForStock(Order order)
    {
        Product product=productRepository.findById(order.getProduct().getPid()).get();
        Customer customer=customerRepository.findById(order.getCustomer().getcId()).get();
//        Twilio.init("ACd400f19e075cfce882e779036328ce0b","2fa5dc456af9cdde28b291681ef203fb");
//        Message.creator(new PhoneNumber("+91"+customer.getPhoneNumber()),new PhoneNumber("+19787235258"),"we need 20000 stock of given medicine"+product.getPid()).create();
        Twilio.init(twilioSmsConfig.getAuthSid(),twilioSmsConfig.getAuthToken());
        Message.creator(new PhoneNumber("+91"+customer.getPhoneNumber()),new PhoneNumber(twilioSmsConfig.getPhoneNumber()),"we need 20000 stock of given medicine "+product.getPid()+" "+product.getpName()).create();
    }
    @Autowired
    JavaMailSender javaMailSender;

//    public void SendMail(Order order)
//    {                                                 //for simple text message sending
//        Customer customer=customerRepository.findById(order.getCustomer().getcId()).get();
//        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//        simpleMailMessage.setTo(customer.getEmail());
//        simpleMailMessage.setFrom("dhruviiucan@gmail.com");
//        simpleMailMessage.setSubject("order");
//        simpleMailMessage.setText("Your order is succesfully done.");
//        javaMailSender.send(simpleMailMessage);
//    }


    public void SendMail()  {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo("dhruvilunagariya@gmail.com");
            mimeMessageHelper.setFrom("dhruviiucan@gmail.com");
            mimeMessageHelper.setSubject("ORDER");
            mimeMessageHelper.setText("We are pleased to inform you that your order has been successfully processed and is now complete.");

            FileSystemResource fileSystemResource = new FileSystemResource(new File("C:\\Users\\Mayur\\Desktop\\SpringBoot myProject\\BillGenerator\\BillGenerator\\src\\main\\resources\\reports\\report.csv"));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            javaMailSender.send(message);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
