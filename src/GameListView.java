import java.awt.event.ActionListener;
import javax.swing.*;

public class GameListView extends JFrame{
    private final JTextArea jTextArea = new JTextArea();
    private final JTextField searchBar = new JTextField(20);
    private final JComboBox<GameListController.gameListSortEnum> sortComboBox;
    private final JComboBox<GameListController.sortDirectionEnum> sortDirectionComboBox;

    GameListView(){
        JPanel gameListViewPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,300);

        gameListViewPanel.add(searchBar);
        gameListViewPanel.add(jTextArea);

        sortComboBox = new JComboBox<>(
                new DefaultComboBoxModel<>(GameListController.gameListSortEnum.values()));
        gameListViewPanel.add(sortComboBox);

        sortDirectionComboBox = new JComboBox<>(
                new DefaultComboBoxModel<>(GameListController.sortDirectionEnum.values()));
        gameListViewPanel.add(sortDirectionComboBox);

        this.add(gameListViewPanel);
    }

    public void setjTextArea(String text) {
        jTextArea.setText(text);
    }

    void addSortComboListener(ActionListener listenForSort) {sortComboBox.addActionListener(listenForSort);}
    void addSortDirectionComboListener(ActionListener listenForSort) {sortDirectionComboBox.addActionListener(listenForSort);}
    void addSearchBarListener(ActionListener listenForSearch) {searchBar.addActionListener(listenForSearch);}
    public GameListController.gameListSortEnum getSortSelection() {
        return (GameListController.gameListSortEnum) sortComboBox.getSelectedItem();
    }

    public GameListController.sortDirectionEnum getSortDirectionSelection() {
        return (GameListController.sortDirectionEnum) sortDirectionComboBox.getSelectedItem();
    }

    public String getSearchBarText() {
        return searchBar.getText();
    }

    public void UpdateView() {}
}
