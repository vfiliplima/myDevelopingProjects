package org.academiadecodigo.offstring.prankguru.services;

import org.academiadecodigo.offstring.prankguru.models.Prank;
import org.academiadecodigo.offstring.prankguru.models.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrankManager {

    private List<Prank> prankList;

    public List<Prank> getPrankList() {
        if (prankList == null) {
            createPranks();
        }
        return prankList;
    }


    public boolean exists(Integer id) {
        if (prankList == null) {
            createPranks();
        }
        for (Prank p : prankList) {
            if(p.getId().equals(id)) {
                return true;
            }
        } return false;
    }


    public void addPrank(Prank prank) {
        int savingId = prankList.size();
        prank.setId(prankList.get(savingId -1).getId() + 1);
        prankList.add(prank);
    }

    public void deletePrank(Integer id) {
        prankList.removeIf(prank -> prank.getId().equals(id));
    }


    public List<Prank> createPranks() {
        prankList = new ArrayList<>();
        prankList.add(new Prank( 1,"Bucket door", "Prank in the supermarket with a BUCKET! The look on peoples faces is priceless!!", "1", "indoors", "easy", "https://www.youtube.com/embed/TRyMBMGW58A?start=67" ));
        prankList.add(new Prank( 2,"Throwing stuff in market", "Throwing things at Strangers in the Supermarket! It's raining things and we don't know where they are coming from!", "1", "indoors", "medium", "https://www.youtube.com/embed/Zjs5G7O8ydw?start=115"));
        prankList.add(new Prank( 3,"Telekinetic event", "What if telekinesis was real? How would you react? An hidden camera experiment captures the reactions of unsuspecting customers at a New York City coffee shop as they witness a telekinetic event.", "5", "indoors", "hard", "https://www.youtube.com/embed/VlOxlSOr3_M?start=39" ));
        prankList.add(new Prank( 4,"Adjacent toilet", "As any good boardroom, this one has an adjacent toilet for the convenience of all meeting attendees.", "5", "outdoor", "easy", "https://www.youtube.com/embed/xAEaA31EdtU"));
        prankList.add(new Prank( 5,"Car lift", "What if you had the strength to lift a car with your bare hands? We gave that power to Jenni and dressed her up as a New York City meter maid. Hidden cameras captured the reactions of people witnessing her teaching a rude cab driver a lesson.", "2", "outdoor", "medium", "https://www.youtube.com/embed/U7zb7qnZBvs?start=26" ));
        prankList.add(new Prank( 6,"Crazy karate", "Watch what happens when unsuspecting New Yorkers witness a crazy karate prank where a street lamp, a motorcycle and a water hydrant get destroyed.", "3", "outdoor", "hard", "https://www.youtube.com/embed/cgIbzjjbdw8?start=12"));
        prankList.add(new Prank( 7,"Persistent foam", "Going sneaky behind people taking showers at the beach and kept on adding more shampoo on their hair as they went crazy trying to wash it off!", "1", "beach", "eady", "https://www.youtube.com/embed/wVKI2yEIVvM?start=31" ));
        prankList.add(new Prank( 8,"Scary bush", "Are you afraid of bushes? No? You should! Meet the BushMan!", "1", "beach", "medium", "https://www.youtube.com/embed/xd24AFveIkE"));
        prankList.add(new Prank( 9,"Skunk prank", "Something smells and it ain't a cat! See the hilarious reactions of people who think a skunk has just gone for a number 2, right here!", "1", "beach", "hard", "https://www.youtube.com/embed/8CFUwxaGzJc?start=85" ));
        prankList.add(new Prank( 10,"Privacy", "Sorry can I have my privacy ?", "1", "elevator", "easy", "https://www.youtube.com/embed/TWwM5NphmeI?start=30"));
        prankList.add(new Prank( 11,"Busy elevator", "Please take the stairs, elevator is busy!", "1", "elevator", "medium", "https://www.youtube.com/embed/YIMLrkBQhZU" ));
        prankList.add(new Prank( 12,"Elevator fly", "What if you could fly above the sky in an elevator?", "1", "elevator", "hard", "https://www.youtube.com/embed/fSK5MuI6M6I?start=40"));
        Prank prank = getPrank(1);
        prank.createReview(1,"Earl","ChickenFoot","5","+18");
        prank.createReview(2,"Zé Soneca","I don't know, I was sleeping","5","zzz");

        return prankList;
    }


    public List<Prank> filteredPranks (String environment, String difficulty, String participants) {

        List<Prank> filteredPrankList = new ArrayList<>();

        for (Prank prank : prankList) {

            if ((environment.equals(prank.getEnvironment()) || environment.equals("any")) &&
                    (difficulty.equals(prank.getDifficulty()) || difficulty.equals("any")) &&
                    (participants.equals(prank.getParticipants()) || participants.equals("any"))) {

                filteredPrankList.add(prank);
            }
        }
        return filteredPrankList;
    }

    public void addReview(Integer id, String username, String title, String stars, String content) {
        Prank prank = null;

        for (Prank prank1 : prankList) {
            if(prank1.getId().equals(id)){
                prank = prank1;
            }
        }

        List<Review> listReviews = prank.getReviews();
        Integer size = listReviews.size();
        Integer reviewId;
        if (size != 0) {
            reviewId = listReviews.get(size - 1).getId();
        } else {
            reviewId = 0;
        }

        prank.createReview(reviewId + 1,username, title, stars, content);
    }

    public Prank getPrank(Integer id) {
       Prank prank = null;
        for (Prank pranks : prankList) {
            if(pranks.getId().equals(id)) {
                prank = pranks;
            }
        }
        return prank;
    }
}
