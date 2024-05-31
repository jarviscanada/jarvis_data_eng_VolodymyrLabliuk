package ca.jarvis.iex;

import javax.persistence.*;

@Entity
@Table(name = "security_order")
public class SecurityOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "price")
    private Double price;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "ticker", insertable = false, updatable = false)
    private Quote quote;

    // Getters and Setters
}

