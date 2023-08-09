package com.flore.taskagenda.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Class for hashing words.
 * */
public class Hash {

    /**
     * Hashing a word.
     * @param word the word to hashing.
     * @return the hashing of the word.
     * */
    public static String hashing(String word) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.hash(1, 1024, 1, word);
    }

    /**
     * Verifies a hashing.
     * @param hash the hash.
     * @param word the word to verify.
     * @return true if the hashing is equal, false in other case.
     * */
    public static boolean verifyHash(String hash, String word) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(hash, word);
    }

}
