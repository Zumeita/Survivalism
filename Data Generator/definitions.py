
from typing import Dict, List, NamedTuple, Sequence, Optional

Vegetation = NamedTuple('Vegetation', min_temp=float, max_temp=float, min_rain=float, max_rain=float, category=str, variants=int)
Food = NamedTuple('Food', edible=bool, hunger=float, longevity=int, category=str)
Seed = NamedTuple('Seed', min_temp=float, max_temp=float, min_rain=float, max_rain=float, well_draining=bool, moisture_retentive=bool)

VEG: Dict[str, Vegetation] = {
    'sagebrush': Vegetation(0.5, 1.8, 0.0, 1.0, 'herb', 4),
    'bitterbrush': Vegetation(0.5, 1.8, 0.0, 1.0, 'herb', 2),
    'milkvetch': Vegetation(0.5, 1.8, 0.0, 1.0, 'herb', 2),
    'adonis': Vegetation(0.5, 1.8, 0.0, 1.0, 'flower', 2),
    'sweet_vernal': Vegetation(0.5, 1.8, 0.0, 1.0, 'grass', 4),
    'blue_grama' : Vegetation(0.5, 1.8, 0.0, 1.0, 'grass', 8),
    'wild_rhubarb' : Vegetation(0.5, 1.8, 0.0, 1.0, 'wild_veg', 0)
}

FOOD: Dict[str, Food] = {
    'sagebrush': Food(True, 0.1, 3, 'herb'),
    'milkvetch': Food(False, 0.0, 3, 'herb'),
    'bitterbrush_leaves': Food(True, 0.1, 4, 'herb'),
    'rhubarb': Food(False, 0.2, 7, 'veg')
}

SEEDS: Dict[str, Seed] = {
    'rhubarb': Seed(0.5, 1.8, 0.1, 1.0, True, False)
}