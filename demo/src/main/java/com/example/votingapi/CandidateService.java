package com.example.votingapi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final Map<String, Candidate> candidates = new ConcurrentHashMap<>();

    // Add a new candidate
    public void addCandidate(String name) {
        candidates.putIfAbsent(name, new Candidate(name));
    }

    // Cast a vote for a candidate
    public int castVote(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate does not exist.");
        }
        candidate.incrementVote();
        return candidate.getVoteCount();
    }

    // Get vote count for a candidate
    public int getVoteCount(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate does not exist.");
        }
        return candidate.getVoteCount();
    }

    // List all candidates and their votes
    public Map<String, Integer> listVotes() {
        Map<String, Integer> voteCounts = new HashMap<>();
        for (Map.Entry<String, Candidate> entry : candidates.entrySet()) {
            voteCounts.put(entry.getKey(), entry.getValue().getVoteCount());
        }
        return voteCounts;
    }

    // Get the candidate with the highest vote count (the winner)
    public String getWinner() {
        return candidates.values().stream()
                .max(Comparator.comparingInt(Candidate::getVoteCount))
                .map(Candidate::getName)
                .orElse("No candidates available");
    }
}
