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
    void loadDictionaryData_ValidFile_ShouldLoadWords() throws IOException {
        SuggestionEngine suggestionEngine = new SuggestionEngine();
        Path dictionaryFilePath = Paths.get("path/to/your/words.txt");  // Replace with the actual path

        // Load dictionary data from file
        suggestionEngine.loadDictionaryData(dictionaryFilePath);

        // Check if the loaded word is present in the dictionary
        assertTrue(suggestionEngine.getWordSuggestionDB().containsKey("example"));
    }

}