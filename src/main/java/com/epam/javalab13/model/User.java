package com.epam.javalab13.model;

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

	public User(){}

	public User(int id, String fullName, int age, Gender gender, String email, String login, String password, double balance, String avatarUrl, Role role, Language language, boolean isBanned) {
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

	public void setBanned(boolean banned) {
		isBanned = banned;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (age != user.age) return false;
		if (Double.compare(user.balance, balance) != 0) return false;
		if (isBanned != user.isBanned) return false;
		if (fullName != null ? !fullName.equals(user.fullName) : user.fullName != null) return false;
		if (gender != user.gender) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null) return false;
		if (role != user.role) return false;
		return language == user.language;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
		result = 31 * result + age;
		result = 31 * result + (gender != null ? gender.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		temp = Double.doubleToLongBits(balance);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (language != null ? language.hashCode() : 0);
		result = 31 * result + (isBanned ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", age=" + age +
				", gender=" + gender +
				", email='" + email + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", balance=" + balance +
				", avatarUrl='" + avatarUrl + '\'' +
				", role=" + role +
				", language=" + language +
				", isBanned=" + isBanned +
				'}';
	}
}
