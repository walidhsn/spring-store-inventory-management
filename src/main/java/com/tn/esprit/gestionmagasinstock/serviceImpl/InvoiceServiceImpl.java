package com.tn.esprit.gestionmagasinstock.serviceImpl;

import com.tn.esprit.gestionmagasinstock.dao.ClientDao;
import com.tn.esprit.gestionmagasinstock.dao.InvoiceDao;
import com.tn.esprit.gestionmagasinstock.dao.InvoiceDetailDao;
import com.tn.esprit.gestionmagasinstock.entity.Client;
import com.tn.esprit.gestionmagasinstock.entity.Invoice;
import com.tn.esprit.gestionmagasinstock.entity.InvoiceDetail;
import com.tn.esprit.gestionmagasinstock.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceDao invoiceDao;
    private final InvoiceDetailDao invoiceDetailDao;
    private final ClientDao clientDao;
    @Autowired
    public InvoiceServiceImpl(InvoiceDao invoiceDao,InvoiceDetailDao invoiceDetailDao,ClientDao clientDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceDetailDao = invoiceDetailDao;
        this.clientDao = clientDao;
    }

    @Override
    public List<Invoice> retrieveAllInvoices() {
        return invoiceDao.findAll();
    }

    @Override
    public void cancelInvoice(Long id) {
        Optional<Invoice> optionalInvoice = invoiceDao.findById(id);
        Invoice result = optionalInvoice.orElse(null);
        if(result!=null){
            if(!result.getActive()){
                invoiceDao.delete(result);
            }else{
                System.out.println("Error : Invoice is already Active.");
            }
        }else{
            System.out.println("Error : Invoice doesn't exist.");
        }

    }

    @Override
    public Invoice retrieveInvoice(Long id) {
        Optional<Invoice> optionalInvoice = invoiceDao.findById(id);
        return optionalInvoice.orElse(null);
    }

    @Override
    public Invoice createInvoiceDto(Long clientId, Invoice invoice, InvoiceDetail invoiceDetail) {
        Optional<Client> optionalClient = clientDao.findById(clientId);
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            invoice.setClient(client);
            Invoice result = invoiceDao.save(invoice);
            invoiceDetail.setInvoice(result);
            invoiceDetailDao.save(invoiceDetail);
            clientDao.save(client);
            return  result;
        }
        return null;
    }

    @Override
    public List<Invoice> getFacturesByClient(Long idClient) {
        List<Invoice> invoiceList = invoiceDao.findInvoicesByClientId(idClient);
        return invoiceList;
    }

    @Override
    public Invoice addFacture(Invoice f, Long idClient) {
        Optional<Client> optionalClient = clientDao.findById(idClient);
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            f.setClient(client);
            f.setInvoiceDate(new Date());
            float totalAmount=0;
            float totalDiscount=0;
            for(InvoiceDetail detail : f.getInvoiceDetails()){
                totalAmount += detail.getTotalPrice();
                totalDiscount += detail.getDiscountAmount();
            }
            float invoiceAmount = totalAmount - totalDiscount;
            f.setInvoiceAmount(invoiceAmount);
            f.setDiscountAmount(totalDiscount);
        }
        return invoiceDao.save(f);
    }
}
