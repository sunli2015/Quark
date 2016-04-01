package cn.org.quark.admin.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CORE_MODULE")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","resources"})
public class CoreModule implements java.io.Serializable {

	private static final long serialVersionUID = -3089192096580606222L;
	@Id
	@Column(length = 32,name="OID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String oid;
	@Column(name="ORDERID")
	private Long orderid;
	@Column(length = 100, name = "MODULE_NAME")
	private String moduleName;
	@Column(length = 200, name = "ENTITY_CLASS_NAME")
	private String entityClassName;
	@Column(length = 200, name = "PROCESS_DEF_NAME")
	private String processDefName;
	@Column(name = "PROCESS_RUN_VERSION")
	private Long processRunVersion;
	@Column(name = "PROCESS_LAST_UPDATE")
	private Date processLastUpdate;
	@Column(length = 200, name = "VIEW_PAGE_URL")
	private String viewPageUrl;
	@Column(length = 200, name = "EDIT_PAGE_URL")
	private String editPageUrl;

	@OneToMany(mappedBy = "module", cascade = CascadeType.REMOVE)
	private Set<CoreResource> resources = new HashSet<CoreResource>(0);

	@ManyToOne
	@JoinColumn(name = "PARENT_MODULE_ID")
	private CoreModule parentModule;

	@OneToMany(mappedBy = "parentModule", cascade = CascadeType.REMOVE)
	private Set<CoreModule> subModules = new HashSet<CoreModule>(0);

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Long getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public CoreModule getParentModule() {
		return parentModule;
	}

	public void setParentModule(CoreModule parentModule) {
		this.parentModule = parentModule;
	}

	public Set<CoreResource> getResources() {
		return resources;
	}

	public void setResources(Set<CoreResource> resources) {
		this.resources = resources;
	}

	public Set<CoreModule> getSubModules() {
		return subModules;
	}

	public void setSubModules(Set<CoreModule> subModules) {
		this.subModules = subModules;
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	public Long getProcessRunVersion() {
		return processRunVersion;
	}

	public void setProcessRunVersion(Long processRunVersion) {
		this.processRunVersion = processRunVersion;
	}

	public Date getProcessLastUpdate() {
		return processLastUpdate;
	}

	public void setProcessLastUpdate(Date processLastUpdate) {
		this.processLastUpdate = processLastUpdate;
	}

	public String getViewPageUrl() {
		return viewPageUrl;
	}

	public void setViewPageUrl(String viewPageUrl) {
		this.viewPageUrl = viewPageUrl;
	}

	public String getEditPageUrl() {
		return editPageUrl;
	}

	public void setEditPageUrl(String editPageUrl) {
		this.editPageUrl = editPageUrl;
	}

}