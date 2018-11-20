package com.dpconsole.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.web.multipart.MultipartFile;

import com.dpconsole.service.Configuration;

@Entity
@Table(name="home_page_configs")
public class HomePage {
	
		@Id
		@GeneratedValue
		@Column(name="id", nullable=false)
		private long id;
		
		@Version
	    @Column(name="version", nullable=false)
		private  long version;
		
		@Column(name="about_college")
		private String aboutCollege;
		
		@Column(name="mission")
		private String mission;
		
		@Column(name="vision")
		private String vision;
		
		@Column(name="header_text")
		private String headerText;
		
		@Column(name="header_city")
		private String headerCity;
		
		@Column(name="logo_name")
		private String collegeLogoName;
		
		@Column(name="principal_image_name")
		private String principalPhotoName;
		
		@Column(name="principal_name")
		private String principalName;
		
		@Column(name="principal_qualification")
		private String principalQualification;
		
		@Column(name="show_image_slider")
		private boolean showImageSlider;
		
		@Transient
		private MultipartFile collegeLogo;
		
		@Transient
		private MultipartFile principalPhoto;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		public long getVersion() {
			return version;
		}
		
		public void setVersion(long version) {
			this.version = version;
		}

		public String getAboutCollege() {
			return aboutCollege;
		}

		public void setAboutCollege(String aboutCollege) {
			this.aboutCollege = aboutCollege;
		}

		public String getMission() {
			return mission;
		}

		public void setMission(String mission) {
			this.mission = mission;
		}

		public String getVision() {
			return vision;
		}

		public void setVision(String vision) {
			this.vision = vision;
		}

		public String getHeaderText() {
			return headerText;
		}

		public void setHeaderText(String headerText) {
			this.headerText = headerText;
		}
		
		public String getHeaderCity() {
			return headerCity;
		}

		public void setHeaderCity(String headerCity) {
			this.headerCity = headerCity;
		}

		public String getCollegeLogoName() {
			return collegeLogoName;
		}

		public void setCollegeLogoName(String collegeLogoName) {
			this.collegeLogoName = collegeLogoName;
		}

		public MultipartFile getCollegeLogo() {
			return collegeLogo;
		}

		public void setCollegeLogo(MultipartFile collegeLogo) {
			this.collegeLogo = collegeLogo;
		}

		public MultipartFile getPrincipalPhoto() {
			return principalPhoto;
		}

		public void setPrincipalPhoto(MultipartFile principalPhoto) {
			this.principalPhoto = principalPhoto;
		}

		public String getPrincipalPhotoName() {
			return principalPhotoName;
		}

		public void setPrincipalPhotoName(String principalPhotoName) {
			this.principalPhotoName = principalPhotoName;
		}

		@Transient
		public String getCollegeLogoRelativePath() {
			return Configuration.Header_Logo_Dir_Name + "/" + collegeLogoName;
		}

		@Transient
		public String getPrincipalPhotoRelativePath() {
			return Configuration.Principal_Photo_Dir_Name + "/" + principalPhotoName;
		}

		public String getPrincipalName() {
			return principalName;
		}

		public void setPrincipalName(String principalName) {
			this.principalName = principalName;
		}

		public String getPrincipalQualification() {
			return principalQualification;
		}

		public void setPrincipalQualification(String principalQualification) {
			this.principalQualification = principalQualification;
		}

		/*public CustomStyle getCustomStyle() {
			return customStyle;
		}

		public void setCustomStyle(CustomStyle customStyle) {
			this.customStyle = customStyle;
		}*/

		public boolean isShowImageSlider() {
			return showImageSlider;
		}

		public void setShowImageSlider(boolean showImageSlider) {
			this.showImageSlider = showImageSlider;
		}
}

