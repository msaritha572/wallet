package com.example.wallet.controller;

import com.example.wallet.exception.ResourceNotFoundException;
import com.example.wallet.model.Ticket;
import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @PostMapping("/wallet")
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet createWallet(@RequestBody Wallet wallet){

        return walletRepository.save(wallet);
    }

    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> getWallets(){
        return ResponseEntity.ok(walletRepository.findAll());
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable UUID id) throws ResourceNotFoundException{
        Wallet wallet= walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this id :: " + id));;
        return ResponseEntity.ok(wallet);
    }

    @PatchMapping("/wallets/{id}")
    public ResponseEntity<Object> updateWalletAmount(@PathVariable UUID id, @RequestBody Wallet patchWallet) throws ResourceNotFoundException {
        Wallet wallet= walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this id :: " + id));;
        wallet.setAmount(wallet.getAmount().add(patchWallet.getAmount()));
        walletRepository.save(wallet);
        return ResponseEntity.ok(wallet);
    }

    @PutMapping("/wallets/{id}")
    public ResponseEntity<Wallet> buyTicket(@PathVariable UUID id, @RequestBody Ticket ticket) throws ResourceNotFoundException {
        Wallet wallet= walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this id :: " + id));;
        wallet.setAmount(wallet.getAmount().subtract(ticket.getFare()));
        wallet.getTickets_issued().add(ticket);
        return ResponseEntity.ok(walletRepository.save(wallet));
    }
}
