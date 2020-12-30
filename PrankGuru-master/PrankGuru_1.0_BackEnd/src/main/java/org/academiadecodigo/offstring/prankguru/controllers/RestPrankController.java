package org.academiadecodigo.offstring.prankguru.controllers;

import org.academiadecodigo.offstring.prankguru.models.Prank;
import org.academiadecodigo.offstring.prankguru.models.PrankDto;
import org.academiadecodigo.offstring.prankguru.models.Review;
import org.academiadecodigo.offstring.prankguru.models.ReviewDto;
import org.academiadecodigo.offstring.prankguru.services.PrankManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/prank")
public class RestPrankController {

    private PrankManager prankManager;

    @Autowired
    public void setPrankManager(PrankManager prankManager) {
        this.prankManager = prankManager;
    }

    @RequestMapping(method = RequestMethod.GET, path = { "/", ""})
    public ResponseEntity<List<Prank>> listPranks () {

        List<Prank> prankList = prankManager.getPrankList();

        return new ResponseEntity<>(prankList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Prank> showPrank (@PathVariable Integer id) {

        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (prankManager.exists(id)) {
           Prank prank =  prankManager.getPrank(id);
            return new ResponseEntity<>(prank, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{environment}/{difficulty}/{participants}")
    public ResponseEntity<List<Prank>> filteredPranks (@PathVariable String environment, @PathVariable String difficulty, @PathVariable String participants) {

        List<Prank> prankList = prankManager.filteredPranks(environment, difficulty, participants);

        return new ResponseEntity<>(prankList, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> deletePrank(@PathVariable Integer id) {

        prankManager.deletePrank(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST, value = "/addprank", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Prank>> addPrank(@RequestBody PrankDto prankDto) {

        Prank prank = new Prank(prankDto.getId(), prankDto.getPrankName(),prankDto.getDescription(), prankDto.getParticipants(),prankDto.getEnvironment(), prankDto.getDifficulty(), prankDto.getUrl());

        prankManager.addPrank(prank);

        List<Prank> prankList = prankManager.getPrankList();

        return new ResponseEntity<>(prankList, HttpStatus.CREATED);
    }

    @RequestMapping( method = RequestMethod.POST, value = "/{id}/addreview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto, @PathVariable Integer id) {

        Prank prank = prankManager.getPrank(id);

       prankManager.addReview(id,reviewDto.getUsername(), reviewDto.getTitle(), reviewDto.getStars(), reviewDto.getContent());

       //prank.createReview());

        List<Review> list = prank.getReviews();

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

}
