
from typing import Dict, List, NamedTuple, Sequence, Optional

def lang(key: str, *args) -> str:
    return ((key % args) if len(args) > 0 else key).replace('_', ' ').replace('/', ' ').title()


def lang_enum(name: str, values: Sequence[str]) -> Dict[str, str]:
    return dict(('tfc.enum.%s.%s' % (name, value), lang(value)) for value in values)

STANDARD_LANG = {
	
	#World
	'generator.survivalism.survivalism': 'Survivalism Worldgen',

	# Item Groups
	'itemGroup.survivalism.vegetation': 'Vegetation',
	'itemGroup.survivalism.food': 'Food',
	'itemGroup.survivalism.seeds': 'Seeds'

}