package com.tn.esprit.gestionmagasinstock.service;

import com.tn.esprit.gestionmagasinstock.entity.Invoice;
import com.tn.esprit.gestionmagasinstock.entity.InvoiceDetail;

import java.util.List;

public interface InvoiceService {
    List<Invoice> retrieveAllInvoices();
    void cancelInvoice(Long id);
    Invoice retrieveInvoice(Long id);
    Invoice createInvoiceDto(Long clientId, Invoice invoice, InvoiceDetail invoiceDetail);
    List<Invoice> getFacturesByClient(Long idClient);
    Invoice addFacture(Invoice f, Long idClient);
}
