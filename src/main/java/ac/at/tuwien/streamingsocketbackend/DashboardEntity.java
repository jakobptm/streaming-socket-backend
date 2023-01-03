package ac.at.tuwien.streamingsocketbackend;

import java.util.Objects;

public class DashboardEntity {

    private String id;

    private String title;

    private double lat;

    private double lng;

    private double distance;

    private double avgSpeed;

    public DashboardEntity() {
    }

    public DashboardEntity(String id, String title, double lat, double lng, double distance, double avgSpeed) {
        this.id = id;
        this.title = title;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
        this.avgSpeed = avgSpeed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DashboardEntity that)) return false;

        if (Double.compare(that.lat, lat) != 0) return false;
        if (Double.compare(that.lng, lng) != 0) return false;
        if (Double.compare(that.distance, distance) != 0) return false;
        if (Double.compare(that.avgSpeed, avgSpeed) != 0) return false;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(avgSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DashboardEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", distance=" + distance +
                ", avgSpeed=" + avgSpeed +
                '}';
    }
}
