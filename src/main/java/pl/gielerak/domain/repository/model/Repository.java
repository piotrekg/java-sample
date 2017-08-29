package pl.gielerak.domain.repository.model;

import org.json.JSONObject;
import pl.gielerak.domain.date.model.DateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Repository {
    final private FullName fullName;
    final private String description;
    final private Integer stars;
    final private String cloneUrl;
    private final LocalDateTime createdAt;

    public Repository(
            FullName fullName,
            String description,
            Integer stars,
            String cloneUrl,
            LocalDateTime createdAt
    ) {
        this.fullName = fullName;
        this.description = description;
        this.stars = stars;
        this.cloneUrl = cloneUrl;
        this.createdAt = createdAt;
    }

    public static Repository fromJsonObject(JSONObject data) {
        Repository repository = new Repository(
                FullName.fromString(data.getString("full_name")),
                data.getString("description"),
                data.getInt("stargazers_count"),
                data.getString("clone_url"),
                LocalDateTime.parse(
                        data.getString("created_at"),
                        DateTimeFormatter.ofPattern(DateFormat.ISO8601.format())
                )
        );

        return repository;
    }

    public static Repository fromJson(JSONObject data) {
        return new Repository(
                FullName.fromString(data.getJSONObject("fullName").getString("fullName")),
                data.getString("description"),
                data.getInt("stars"),
                data.getString("cloneUrl"),
                LocalDateTime.parse(
                        data.getString("createdAt"),
                        DateTimeFormatter.ofPattern(DateFormat.JSON.format())
                )
        );
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject(this);
        return jsonObject.toString();
    }

    public FullName getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStars() {
        return stars;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
