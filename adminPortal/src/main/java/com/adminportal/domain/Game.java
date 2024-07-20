package com.adminportal.domain;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String linkOfCompany;
	private int AgeRating;
	private double starRating;
	private String category;
	private int off;
	private double listPrice;
	private double ourPrice;
	private boolean active = true;
	
	@Column(columnDefinition="text")
	private String description;
	private int stockAvailable;
	
	@OneToMany(mappedBy="game")
	@JsonIgnore
	private List<GameCartItem> gameCartItme;
	
	
	@Transient
	private MultipartFile image;
	
    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLinkOfCompany() {
		return linkOfCompany;
	}
	public void setLinkOfCompany(String linkOfCompany) {
		this.linkOfCompany = linkOfCompany;
	}
	public int getAgeRating() {
		return AgeRating;
	}
	public void setAgeRating(int ageRating) {
		AgeRating = ageRating;
	}
	public double getStarRating() {
		return starRating;
	}
	public void setStarRating(double starRating) {
		this.starRating = starRating;
	}
	public double getListPrice() {
		return listPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	public double getOurPrice() {
		return ourPrice;
	}
	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStockAvailable() {
		return stockAvailable;
	}
	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<GameCartItem> getGameCartItme() {
		return gameCartItme;
	}

	public void setGameCartItme(List<GameCartItem> gameCartItme) {
		this.gameCartItme = gameCartItme;
	}
	
	
	
	
}
