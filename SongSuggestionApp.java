import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongSuggestionApp extends JFrame {

    private final Map<String, String> songAttributes;

    private JComboBox<String> moodComboBox;
    private JComboBox<String> genreComboBox;

    public SongSuggestionApp() {
        // Initialize sample songs and their attributes
        songAttributes = new HashMap<>();
        songAttributes.put("Happy Pop Song", "Happy, Pop");
        songAttributes.put("Sad Ballad Song", "Sad, Ballad");
        songAttributes.put("Energetic Rock Song", "Energetic, Rock");
        songAttributes.put("Chill Indie Song", "Chill, Indie");
        songAttributes.put("Romantic Jazz Song", "Romantic, Jazz");
        songAttributes.put("Motivational Hip Hop Song", "Motivational, Hip Hop");

        // Create GUI components
        JLabel moodLabel = new JLabel("Select your mood:");
        JLabel genreLabel = new JLabel("Select your preferred genre:");

        String[] moodOptions = {"Happy", "Sad", "Energetic", "Chill", "Romantic", "Motivational"};
        moodComboBox = new JComboBox<>(moodOptions);

        String[] genreOptions = {"Pop", "Ballad", "Rock", "Indie", "Jazz", "Hip Hop"};
        genreComboBox = new JComboBox<>(genreOptions);

        JButton suggestButton = new JButton("Get Song Suggestions");

        // Set layout manager
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to the frame
        add(moodLabel);
        add(moodComboBox);
        add(genreLabel);
        add(genreComboBox);
        add(suggestButton);

        // Add action listener to the button
        suggestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSuggestions();
            }
        });

        // Set frame properties
        setTitle("Song Suggestion App");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
    }

    private void showSuggestions() {
        // Get user selections from combo boxes
        String selectedMood = (String) moodComboBox.getSelectedItem();
        String selectedGenre = (String) genreComboBox.getSelectedItem();

        // Find matching songs based on user preferences
        List<String> suggestedSongs = getSuggestedSongs(songAttributes, selectedMood, selectedGenre);

        // Display suggestions in a dialog box
        String message;
        if (suggestedSongs.isEmpty()) {
            message = "No matching songs found. Try adjusting your preferences.";
        } else {
            StringBuilder sb = new StringBuilder("Suggestions based on your preferences:\n");
            for (String song : suggestedSongs) {
                sb.append("- ").append(song).append("\n");
            }
            message = sb.toString();
        }
        JOptionPane.showMessageDialog(this, message);
    }

    private List<String> getSuggestedSongs(Map<String, String> songAttributes, String mood, String genre) {
        List<String> suggestedSongs = new ArrayList<>();

        for (Map.Entry<String, String> entry : songAttributes.entrySet()) {
            String song = entry.getKey();
            String attributes = entry.getValue();

            // Check if the song matches the user preferences
            if (attributes.contains(mood) && attributes.contains(genre)) {
                suggestedSongs.add(song);
            }
        }

        return suggestedSongs;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SongSuggestionApp().setVisible(true);
            }
        });
    }
}