/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;


@SessionScoped
@ManagedBean
public class GuestPreferences implements Serializable {

    private String layoutPrimaryColor = "blue";

    private String componentTheme = "blue";
    
    private String menuTheme = "blue";
    
    private String menuColor = "light";
    
    private String selectedMenuColor;

    private boolean horizontalMenu;
    
    private boolean darkMode;

    private List<ComponentTheme> componentThemes;
    
    private Map<String, List<MenuTheme>> menuColors;

    public GuestPreferences() {
        this.menuColors = new HashMap<String, List<MenuTheme>>();
        this.componentThemes = new ArrayList<ComponentTheme>();
    }

    @PostConstruct
    public void init() {        
        this.selectedMenuColor = menuColor;
        
        componentThemes.add(new ComponentTheme("Blue", "blue", "blue.svg"));
        componentThemes.add(new ComponentTheme("Green", "green", "green.svg"));
        componentThemes.add(new ComponentTheme("Yellow", "yellow", "yellow.svg"));
        componentThemes.add(new ComponentTheme("Cyan", "cyan", "cyan.svg"));
        componentThemes.add(new ComponentTheme("Purple", "purple", "purple.svg"));
        componentThemes.add(new ComponentTheme("Orange", "orange", "orange.svg"));
        componentThemes.add(new ComponentTheme("Teal", "teal", "teal.svg"));
        componentThemes.add(new ComponentTheme("Magenta", "magenta", "magenta.svg"));
        componentThemes.add(new ComponentTheme("Lime", "lime", "lime.svg"));
        componentThemes.add(new ComponentTheme("Brown", "brown", "brown.svg"));
        componentThemes.add(new ComponentTheme("Red", "red", "red.svg"));
        componentThemes.add(new ComponentTheme("Indigo", "indigo", "indigo.svg"));

        /************** Light ********************/
        List<MenuTheme> menuThemes = new ArrayList<MenuTheme>();
        menuThemes.add(new MenuTheme("Blue", "blue", "blue.svg"));
        menuThemes.add(new MenuTheme("Green", "green", "green.svg"));
        menuThemes.add(new MenuTheme("Yellow", "yellow", "yellow.svg"));
        menuThemes.add(new MenuTheme("Cyan", "cyan", "cyan.svg"));
        menuThemes.add(new MenuTheme("Purple", "purple", "purple.svg"));
        menuThemes.add(new MenuTheme("Orange", "orange", "orange.svg"));
        menuThemes.add(new MenuTheme("Teal", "teal", "teal.svg"));
        menuThemes.add(new MenuTheme("Magenta", "magenta", "magenta.svg"));
        menuThemes.add(new MenuTheme("Lime", "lime", "lime.svg"));
        menuThemes.add(new MenuTheme("Brown", "brown", "brown.svg"));
        menuThemes.add(new MenuTheme("Red", "red", "red.svg"));
        menuThemes.add(new MenuTheme("Indigo", "indigo", "indigo.svg"));
        menuColors.put("light", menuThemes);
        
        /************** Dark ********************/
        menuThemes = new ArrayList<MenuTheme>();
        menuThemes.add(new MenuTheme("Blue", "blue", "blue.svg"));
        menuThemes.add(new MenuTheme("Green", "green", "green.svg"));
        menuThemes.add(new MenuTheme("Yellow", "yellow", "yellow.svg"));
        menuThemes.add(new MenuTheme("Cyan", "cyan", "cyan.svg"));
        menuThemes.add(new MenuTheme("Purple", "purple", "purple.svg"));
        menuThemes.add(new MenuTheme("Orange", "orange", "orange.svg"));
        menuThemes.add(new MenuTheme("Teal", "teal", "teal.svg"));
        menuThemes.add(new MenuTheme("Magenta", "magenta", "magenta.svg"));
        menuThemes.add(new MenuTheme("Lime", "lime", "lime.svg"));
        menuThemes.add(new MenuTheme("Brown", "brown", "brown.svg"));
        menuThemes.add(new MenuTheme("Red", "red", "red.svg"));
        menuThemes.add(new MenuTheme("Indigo", "indigo", "indigo.svg"));
        menuColors.put("dark", menuThemes);
        
        /************** Custom ********************/
        menuThemes = new ArrayList<MenuTheme>();
        menuThemes.add(new MenuTheme("Chile", "chile", "chile.png"));
        menuThemes.add(new MenuTheme("Naples", "naples", "naples.png"));
        menuThemes.add(new MenuTheme("Georgia", "georgia", "georgia.png"));
        menuThemes.add(new MenuTheme("Infinity", "infinity", "infinity.png"));
        menuThemes.add(new MenuTheme("Chicago", "chicago", "chicago.png"));
        menuThemes.add(new MenuTheme("Majesty", "majesty", "majesty.png"));
        menuThemes.add(new MenuTheme("Fish", "fish", "fish.png"));
        menuThemes.add(new MenuTheme("Dawn", "dawn", "dawn.png"));
        menuThemes.add(new MenuTheme("Record", "record", "record.png"));
        menuThemes.add(new MenuTheme("Pool", "pool", "pool.png"));
        menuThemes.add(new MenuTheme("Metal", "metal", "metal.png"));
        menuThemes.add(new MenuTheme("China", "china", "china.png"));
        menuThemes.add(new MenuTheme("Table", "table", "table.png"));
        menuThemes.add(new MenuTheme("Panorama", "panorama", "panorama.png"));
        menuThemes.add(new MenuTheme("Barcelona", "barcelona", "barcelona.png"));
        menuThemes.add(new MenuTheme("Underwater", "underwater", "underwater.png"));
        menuThemes.add(new MenuTheme("Symmetry", "symmetry", "symmetry.png"));
        menuThemes.add(new MenuTheme("Rain", "rain", "rain.png"));
        menuThemes.add(new MenuTheme("Utah", "utah", "utah.png"));
        menuThemes.add(new MenuTheme("Wave", "wave", "wave.png"));
        menuThemes.add(new MenuTheme("Flora", "flora", "flora.png"));
        menuThemes.add(new MenuTheme("Speed", "speed", "speed.png"));
        menuThemes.add(new MenuTheme("Canopy", "canopy", "canopy.png"));
        menuThemes.add(new MenuTheme("SanPaolo", "sanpaolo", "sanpaolo.png"));
        menuThemes.add(new MenuTheme("Basketball", "basketball", "basketball.png"));
        menuThemes.add(new MenuTheme("Misty", "misty", "misty.png"));
        menuThemes.add(new MenuTheme("Summit", "summit", "summit.png"));
        menuThemes.add(new MenuTheme("Wall", "wall", "wall.png"));
        menuThemes.add(new MenuTheme("Ferris", "ferris", "ferris.png"));
        menuThemes.add(new MenuTheme("Ship", "ship", "ship.png"));
        menuThemes.add(new MenuTheme("NY", "ny", "ny.png"));
        menuThemes.add(new MenuTheme("Cyan", "cyan", "cyan.png"));
        menuThemes.add(new MenuTheme("Violet", "violet", "violet.png"));
        menuThemes.add(new MenuTheme("Red", "red", "red.png"));
        menuThemes.add(new MenuTheme("Blue", "blue", "blue.png"));
        menuThemes.add(new MenuTheme("Porsuk", "porsuk", "porsuk.png"));
        menuThemes.add(new MenuTheme("Pink", "pink", "pink.png"));
        menuThemes.add(new MenuTheme("Purple", "purple", "purple.png"));
        menuThemes.add(new MenuTheme("Orange", "orange", "orange.png"));
        menuColors.put("custom", menuThemes);
    }
    
    public String getLayoutConfig() {
        StringBuilder sb = new StringBuilder();
        String color = "";
        if ("custom".equals(this.menuColor)) {
            color = this.menuTheme;
        }
        else {
            color = this.isDarkMode() ? "dark" : "light";
        }
        sb.append("layout-menu-").append(color);

        if (this.isHorizontalMenu()) {
            sb.append(" layout-horizontal");
        }

        return sb.toString();
    }

    public String getLayout() {
        return "layout-" + this.layoutPrimaryColor + (this.darkMode ? "-dark" : "-light");
    }
    
    public String getTheme() {
        return this.darkMode ? this.componentTheme + "-dark" : this.componentTheme + "-light";
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
        
        this.menuColor = darkMode ? "dark" : "light";
        this.selectedMenuColor = this.menuColor;
        this.menuTheme = ((MenuTheme)this.menuColors.get(this.menuColor).get(0)).getFile();
    }

    public String getLayoutPrimaryColor() {
        return layoutPrimaryColor;
    }

    public void setLayoutPrimaryColor(String layoutPrimaryColor) {
        this.layoutPrimaryColor = layoutPrimaryColor;
    }

    public String getComponentTheme() {
        return componentTheme;
    }

    public void setComponentTheme(String componentTheme) {
        this.componentTheme = componentTheme;
    }

    public boolean isHorizontalMenu() {
        return horizontalMenu;
    }

    public void setHorizontalMenu(boolean horizontalMenu) {
        this.horizontalMenu = horizontalMenu;
    }

    public String getMenuTheme() {
        return menuTheme;
    }

    public void setMenuTheme(String menuTheme) {
        this.menuTheme = menuTheme;
    }

    public List<ComponentTheme> getComponentThemes() {
        return componentThemes;
    }

    public void setComponentThemes(List<ComponentTheme> componentThemes) {
        this.componentThemes = componentThemes;
    }

    public String getMenuColor() {
        return menuColor;
    }

    public void setMenuColor(String menuColor) {
        this.menuColor = menuColor;
        
        this.selectedMenuColor = menuColor;
        this.menuTheme = ((MenuTheme)this.menuColors.get(menuColor).get(0)).getFile();
    }

    public Map<String, List<MenuTheme>> getMenuColors() {
        return menuColors;
    }

    public void setMenuColors(Map<String, List<MenuTheme>> menuColors) {
        this.menuColors = menuColors;
    }

    public String getSelectedMenuColor() {
        return selectedMenuColor;
    }

    public void setSelectedMenuColor(String selectedMenuColor) {
        this.selectedMenuColor = selectedMenuColor;
    }

    public class MenuTheme {

        String name;
        String file;
        String image;

        public MenuTheme(String name, String file, String image) {
            this.name = name;
            this.file = file;
            this.image = image;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getImage() {
            return this.image;
        }
    }

    public class ComponentTheme {

        String name;
        String file;
        String image;

        public ComponentTheme(String name, String file, String image) {
            this.name = name;
            this.file = file;
            this.image = image;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getImage() {
            return this.image;
        }
    }
}
