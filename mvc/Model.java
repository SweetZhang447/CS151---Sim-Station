package mvc;

public class Model extends Bean {
    private String fileName;
    // when set to true: model has unsaved changes
    // false: there are no unsaved changes
    private boolean unsavedChanges;

    public Model() {
        fileName = null;
        unsavedChanges = false;
    }

    // Sets flag to true, fires property changed event
    public void changed() {
        boolean oldChanged = unsavedChanges;
        unsavedChanges = true;
        firePropertyChange(null, null, null);
    }

    // Helper methods
    public void setUnsavedChanges(boolean unsavedChanges) {
        this.unsavedChanges = unsavedChanges;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }
}
