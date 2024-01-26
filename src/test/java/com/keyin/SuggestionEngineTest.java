package com.keyin;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionEngineTest {

    @Test
    void generateSuggestions_CorrectWord_ShouldReturnEmptyString() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        // Add a known word to the dictionary
        suggestionEngine.getWordSuggestionDB().put("test", 1);

        // The word is in the dictionary, so suggestions should be an empty string
        assertEquals("", suggestionEngine.generateSuggestions("test"));
    }

    @Test
    void generateSuggestions_SingleTypo_ShouldReturnSuggestions() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        // Add a known word to the dictionary
        suggestionEngine.getWordSuggestionDB().put("test", 1);

        // A single typo in the word, expecting suggestions
        assertTrue(suggestionEngine.generateSuggestions("tets").contains("test"));
    }


    @Test
    void generateSuggestions_MultipleSuggestions_ShouldReturnTopSuggestions() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        // Add known words to the dictionary
        suggestionEngine.getWordSuggestionDB().put("programming", 2);
        suggestionEngine.getWordSuggestionDB().put("programmer", 3);
        suggestionEngine.getWordSuggestionDB().put("project", 1);

        // A word with a typo, expecting suggestions
        String suggestions = suggestionEngine.generateSuggestions("programmer");

        // Ensure top suggestions are returned in the correct order
        assertTrue(suggestions.startsWith("programmer\nprogramming\n"));
    }


}