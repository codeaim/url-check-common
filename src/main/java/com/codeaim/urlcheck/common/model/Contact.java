package com.codeaim.urlcheck.common.model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Check check;
    @NotNull
    private String name;
    @NotNull
    private LocalDateTime created;
    @NotNull
    private LocalDateTime updated;
    @Version
    private int version;

    public Contact(
            final Long id,
            final Check check,
            final String name,
            final LocalDateTime created,
            final LocalDateTime updated,
            final int version
    )
    {
        this.id = id;
        this.check = check;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.version = version;
    }

    protected Contact()
    {
    }

    public Long getId()
    {
        return id;
    }

    public Check getCheck()
    {
        return check;
    }

    public String getName()
    {
        return name;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public LocalDateTime getUpdated()
    {
        return updated;
    }

    public int getVersion()
    {
        return this.version;
    }
}
