package com.example.votingapi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // Enter a new candidate
    @PostMapping("/entercandidate")
    public String enterCandidate(@RequestParam String name) {
        candidateService.addCandidate(name);
        return "Candidate " + name + " added successfully.";
    }

    // Cast a vote for a candidate
    @PostMapping("/castvote")
    public String castVote(@RequestParam String name) {
        int updatedVoteCount = candidateService.castVote(name);
        return "Vote cast for " + name + ". Current vote count: " + updatedVoteCount;
    }

    // Get the vote count for a candidate
    @GetMapping("/countvote")
    public String countVote(@RequestParam String name) {
        int voteCount = candidateService.getVoteCount(name);
        return name + " has " + voteCount + " votes.";
    }

    // List all candidates and their votes
    @GetMapping("/listvote")
    public Map<String, Integer> listVotes() {
        return candidateService.listVotes();
    }

    // Get the candidate with the most votes
    @GetMapping("/getwinner")
    public String getWinner() {
        return "The winner is " + candidateService.getWinner();
    }
}
