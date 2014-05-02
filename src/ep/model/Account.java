package ep.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity(name="account")
public class Account implements Serializable {
	
	@Id
	@Column(length=50)
	private String code;
	
	@Column(unique=true)
	private String login;

	private String nickname;
	private String mail;
	private String password;
	
	@ElementCollection
	@CollectionTable(name="account_statut",
			joinColumns=@JoinColumn(name="code_account"),
			uniqueConstraints= @UniqueConstraint(columnNames={"code_account","statut"}))
	@Enumerated(EnumType.STRING)
	@Column(name="statut", length=50)
	private Set<Statut> statuts;
	
//	@ManyToMany(mappedBy="responsables")
//	private Set<Mention> mentions;
//	
//	@ManyToMany(mappedBy="responsables")
//	private Set<Specialite> specialites;
//	
//	@ManyToMany(mappedBy="responsables")
//	private Set<Programme> programmes;
//	
//	@ManyToMany(mappedBy="responsables")
//	private Set<Enseignement> enseignements;
//	
//	@ManyToMany(mappedBy="responsables")
//	private Set<UECat> uecats;
	
	public Account(){}

	public Account(String code, String login, String nickname, String mail, String password, Set<Statut> statuts) {
		super();
		this.code = code;
		this.login = login;
		this.nickname = nickname;
		this.mail = mail;
		this.password = password;
		this.statuts = statuts;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Statut> getStatuts() {
		return statuts;
	}

	public void setStatuts(Set<Statut> statuts) {
		this.statuts = statuts;
	}
}