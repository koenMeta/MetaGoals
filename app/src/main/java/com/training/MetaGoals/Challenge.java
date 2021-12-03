package com.training.MetaGoals;

public class Challenge {

    private String challengeTitle, challengeUrl, challengeDescription;

    public Challenge(String challengeTitle, String challengeUrl, String challengeDescription) {
        this.challengeTitle = challengeTitle;
        this.challengeUrl = challengeUrl;
        this.challengeDescription = challengeDescription;
    }

    public String getChallengeDescription() {
        return challengeDescription;
    }

    public void setChallengeDescription(String challengeDescription) {
        this.challengeDescription = challengeDescription;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public void setChallengeTitle(String challengeTitle) {
        this.challengeTitle = challengeTitle;
    }

    public String getChallengeUrl() {
        return challengeUrl;
    }

    public void setChallengeUrl(String challengeUrl) {
        this.challengeUrl = challengeUrl;
    }
}
