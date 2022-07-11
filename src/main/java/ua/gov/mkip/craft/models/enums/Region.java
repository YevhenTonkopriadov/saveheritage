package ua.gov.mkip.craft.models.enums;

public enum Region {
    AutonomousRepublicOfCrimea("Автономна Республіка Крим"),
    CherkasyRegion("Черкаська область"),
    ChernihivRegion("Чернігівська область"),
    ChernivtsiRegion("Чернівецька область"),
    DnipropetrovskRegion("Дніпропетровська область"),
    DonetskRegion("Донецька область"),
    IvanoFrankivskRegion("Івано-Франківська область"),
    KharkivRegion("Харківська область"),
    KhersonRegion("Херсонська область"),
    KhmelnytskyiRegion("Хмельницька область"),
    KirovohradRegion("Кіровоградська область"),
    KyivRegion("Київська область"),
    LuhanskRegion("Луганська область"),
    LvivRegion("Львівська область"),
    MykolaivRegion("Миколаївська область"),
    OdesaRegion("Одеська область"),
    PoltavaRegion("Полтавська область"),
    RivneRegion("Рівненська область"),
    SumyRegion("Сумська область"),
    TernopilRegion("Тернопільська область"),
    VinnytsiaRegion("Вінницька область"),
    VolynRegion("Волинська область"),
    ZakarpattiaRegion("Закарпатська область"),
    ZaporizhzhiaRegion("Запорізька область"),
    ZhytomyrRegion("Житомирська область"),
    KyivCity("місто Київ"),
    SevastopolCity("місто Севастополь");

    private final String displayValue;

    private Region (String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
