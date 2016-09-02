package com.epam.javalab13.model;

import java.util.List;

import com.epam.javalab13.model.bet.TotalBet;

public class User {
	private int id;
	private String fullName;
	private int age;
	private Gender gender;
	private String email;
	private String login;
	private String password;
	private double balance;
	private String avatarUrl;
	private Role role;
	private Language language;
	private boolean isBanned;

	private List<TotalBet> totalBets;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String fullName, int age, Gender gender, String email, String login, String password,
			double balance, String avatarUrl, Role role, Language language, boolean isBanned) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.login = login;
		this.password = password;
		this.balance = balance;
		this.avatarUrl = avatarUrl;
		this.role = role;
		this.language = language;
		this.isBanned = isBanned;
	}

	public User(String fullName, int age, Gender gender, String email, String login, String password, double balance,
			String avatarUrl, Role role, Language language, boolean isBanned) {
		super();
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.login = login;
		this.password = password;
		this.balance = balance;
		this.avatarUrl = avatarUrl;
		this.role = role;
		this.language = language;
		this.isBanned = isBanned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + (isBanned ? 1231 : 1237);
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (avatarUrl == null) {
			if (other.avatarUrl != null)
				return false;
		} else if (!avatarUrl.equals(other.avatarUrl))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (gender != other.gender)
			return false;
		if (id != other.id)
			return false;
		if (isBanned != other.isBanned)
			return false;
		if (language != other.language)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", age=" + age + ", gender=" + gender + ", email=" + email
				+ ", login=" + login + ", password=" + password + ", balance=" + balance + ", avatarUrl=" + avatarUrl
				+ ", role=" + role + ", language=" + language + ", isBanned=" + isBanned + "]";
	}

	public List<TotalBet> getTotalBets() {
		return totalBets;
	}

	public void setTotalBets(List<TotalBet> totalBets) {
		this.totalBets = totalBets;
	}

}
