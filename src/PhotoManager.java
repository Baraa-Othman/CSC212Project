public class PhotoManager {
    private LinkedList<Photo> photos; // Using your custom LinkedList

    // Constructor
    public PhotoManager() {
        photos = new LinkedList<>();
    }

    // Return all managed photos
    public LinkedList<Photo> getPhotos() {
        return photos;
    }

    // Add a photo
    // Big-Oh is O(1).
    public void addPhoto(Photo p) {
        photos.insert(p);
    }

    // Delete a photo
    // Big-Oh is O(n) where n is the number of photos.
    public void deletePhoto(String path) {
        photos.findFirst();
        for (int i = 0; !photos.empty(); i++) {
            if (photos.retrieve().getPath().equals(path)) {
                photos.remove();
                break;
            }
            if (!photos.last()) {
                photos.findNext();
            } else {
                break;
            }
        }
    }
}
