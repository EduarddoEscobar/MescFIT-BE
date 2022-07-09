package com.mescfit.buckets;

public enum BucketName {
    EXERCISE_LIBRARY_DEV("mescfit-exercise-library-dev"),
    USER_PROFILE_DEV("mescfit-user-profile-dev");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
