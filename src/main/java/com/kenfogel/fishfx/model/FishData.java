package com.kenfogel.fishfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Data bean for fish that overrides toString, hashCode and equals Updated id
 * field to int and updated equals and hashCode
 *
 * @author Ken
 * @version 1.6
 *
 */
public class FishData {

    private IntegerProperty id;
    private StringProperty commonName;
    private StringProperty latin;
    private StringProperty ph;
    private StringProperty kh;
    private StringProperty temp;
    private StringProperty fishSize;
    private StringProperty speciesOrigin;
    private StringProperty tankSize;
    private StringProperty stocking;
    private StringProperty diet;

    /**
     * Non-default constructor
     *
     * @param id
     * @param commonName
     * @param latin
     * @param ph
     * @param kh
     * @param temp
     * @param fishSize
     * @param speciesOrigin
     * @param tankSize
     * @param stocking
     * @param diet
     */
    public FishData(int id, String commonName, String latin, String ph,
            String kh, String temp, String fishSize, String speciesOrigin,
            String tankSize, String stocking, String diet) {
        super();
        this.id = new SimpleIntegerProperty(id);
        this.commonName = new SimpleStringProperty(commonName);
        this.latin = new SimpleStringProperty(latin);
        this.ph = new SimpleStringProperty(ph);
        this.kh = new SimpleStringProperty(kh);
        this.temp = new SimpleStringProperty(temp);
        this.fishSize = new SimpleStringProperty(fishSize);
        this.speciesOrigin = new SimpleStringProperty(speciesOrigin);
        this.tankSize = new SimpleStringProperty(tankSize);
        this.stocking = new SimpleStringProperty(stocking);
        this.diet = new SimpleStringProperty(diet);
    }

    /**
     * Default Constructor
     */
    public FishData() {
        this(-1, "", "", "", "", "", "", "", "", "", "");
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public String getCommonName() {
        return commonName.get();
    }

    public void setCommonName(String commonName) {
        this.commonName.set(commonName);
    }

    public StringProperty getCommonNameProperty() {
        return commonName;
    }

    public String getLatin() {
        return latin.get();
    }

    public void setLatin(String latin) {
        this.latin.set(latin);
    }

    public StringProperty getLatinProperty() {
        return latin;
    }

    public String getPh() {
        return ph.get();
    }

    public void setPh(String ph) {
        this.ph.set(ph);
    }

    public StringProperty getPhProperty() {
        return ph;
    }

    public String getKh() {
        return kh.get();
    }

    public void setKh(String kh) {
        this.kh.set(kh);
    }

    public StringProperty getKhProperty() {
        return kh;
    }

    public String getTemp() {
        return temp.get();
    }

    public void setTemp(String temp) {
        this.temp.set(temp);
    }

    public StringProperty getTempProperty() {
        return temp;
    }

    public String getFishSize() {
        return fishSize.get();
    }

    public void setFishSize(String size) {
        this.fishSize.set(size);
    }

    public StringProperty getFishSizeProperty() {
        return fishSize;
    }

    public String getSpeciesOrigin() {
        return speciesOrigin.get();
    }

    public void setSpeciesOrigin(String speciesOrigin) {
        this.speciesOrigin.set(speciesOrigin);
    }

    public StringProperty getSpeciesOriginProperty() {
        return speciesOrigin;
    }

    public String getTankSize() {
        return tankSize.get();
    }

    public void setTankSize(String tankSize) {
        this.tankSize.set(tankSize);
    }

    public StringProperty getTankSizeProperty() {
        return tankSize;
    }

    public String getStocking() {
        return stocking.get();
    }

    public void setStocking(String stocking) {
        this.stocking.set(stocking);
    }

    public StringProperty getStockingProperty() {
        return stocking;
    }

    public String getDiet() {
        return diet.get();
    }

    public void setDiet(String diet) {
        this.diet.set(diet);
    }

    public StringProperty getDietProperty() {
        return diet;
    }

    @Override
    public String toString() {
        String s = "\n            ID = " + id.get() + "\n" + "   Common Name = "
                + commonName.get() + "\n" + "         Latin = " + latin.get() + "\n"
                + "            ph = " + ph.get() + "\n" + "            kh = " + kh.get()
                + "\n" + "          Temp = " + temp.get() + "\n"
                + "          Size = " + fishSize.get() + "\n" + "Species Origin = "
                + speciesOrigin.get() + "\n" + "     Tank Size = " + tankSize.get() + "\n"
                + "      Stocking = " + stocking.get() + "\n" + "          Diet = "
                + diet.get() + "\n";

        return s;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((commonName.get() == null) ? 0 : commonName.get().hashCode());
        result = prime * result
                + ((diet.get() == null) ? 0 : diet.get().hashCode());
        result = prime * result
                + ((fishSize.get() == null) ? 0 : fishSize.get().hashCode());
        result = prime * result
                + ((kh.get() == null) ? 0 : kh.get().hashCode());
        result = prime * result
                + ((latin.get() == null) ? 0 : latin.get().hashCode());
        result = prime * result
                + ((ph.get() == null) ? 0 : ph.get().hashCode());
        result = prime
                * result
                + ((speciesOrigin.get() == null) ? 0 : speciesOrigin.get()
                        .hashCode());
        result = prime * result
                + ((stocking.get() == null) ? 0 : stocking.get().hashCode());
        result = prime * result
                + ((tankSize.get() == null) ? 0 : tankSize.get().hashCode());
        result = prime * result
                + ((temp.get() == null) ? 0 : temp.get().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FishData other = (FishData) obj;
        if (commonName.get() == null) {
            if (other.commonName.get() != null) {
                return false;
            }
        } else if (!commonName.get().equals(other.commonName.get())) {
            return false;
        }
        if (diet.get() == null) {
            if (other.diet.get() != null) {
                return false;
            }
        } else if (!diet.get().equals(other.diet.get())) {
            return false;
        }
        if (fishSize.get() == null) {
            if (other.fishSize.get() != null) {
                return false;
            }
        } else if (!fishSize.get().equals(other.fishSize.get())) {
            return false;
        }
        if (kh.get() == null) {
            if (other.kh.get() != null) {
                return false;
            }
        } else if (!kh.get().equals(other.kh.get())) {
            return false;
        }
        if (latin.get() == null) {
            if (other.latin.get() != null) {
                return false;
            }
        } else if (!latin.get().equals(other.latin.get())) {
            return false;
        }
        if (ph.get() == null) {
            if (other.ph.get() != null) {
                return false;
            }
        } else if (!ph.get().equals(other.ph.get())) {
            return false;
        }
        if (speciesOrigin.get() == null) {
            if (other.speciesOrigin.get() != null) {
                return false;
            }
        } else if (!speciesOrigin.get().equals(other.speciesOrigin.get())) {
            return false;
        }
        if (stocking.get() == null) {
            if (other.stocking.get() != null) {
                return false;
            }
        } else if (!stocking.get().equals(other.stocking.get())) {
            return false;
        }
        if (tankSize.get() == null) {
            if (other.tankSize.get() != null) {
                return false;
            }
        } else if (!tankSize.get().equals(other.tankSize.get())) {
            return false;
        }
        if (temp.get() == null) {
            if (other.temp.get() != null) {
                return false;
            }
        } else if (!temp.get().equals(other.temp.get())) {
            return false;
        }
        return true;
    }
}
