package hms.cpaas.kuppiya.persistence.mongo;

import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {
    @Id
    private String id;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Version
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", createdBy='").append(createdBy).append('\'');
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedBy='").append(updatedBy).append('\'');
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
