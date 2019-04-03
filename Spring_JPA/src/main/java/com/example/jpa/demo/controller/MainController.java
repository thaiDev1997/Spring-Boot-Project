package com.example.jpa.demo.controller;

import com.example.jpa.demo.dao.BankAccountDAO;
import com.example.jpa.demo.entity.BankAccount;
import com.example.jpa.demo.exception.BankTransactionException;
import com.example.jpa.demo.form.SendMoneyForm;
import com.example.jpa.demo.dto.BankAccountDTO;
import com.example.jpa.demo.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class MainController {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object showBankAccounts() {
        List<BankAccountDTO> list = bankAccountDAO.listBankAccountInfo();
        return list;
    }

    @RequestMapping(value = "/listba", method = RequestMethod.GET)
    public Object showBankAccountsByFullName(@RequestParam(value = "fullname", defaultValue = "") String fullName) {
        List<BankAccount> list = bankAccountRepository.findBankAccountByFullName(fullName);
        return list;
    }

    @RequestMapping(value = "/listbadto", method = RequestMethod.GET)
    public List<BankAccountDTO> showBankAccountsInfoByFullName(@RequestParam(value = "fullname", defaultValue = "") String fullName) {
        List<BankAccountDTO> list = bankAccountRepository.findBankAccountDTOByFullName(fullName);
        return list;
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {

        System.out.println("Send Money: " + sendMoneyForm.getAmount());

        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }
}
