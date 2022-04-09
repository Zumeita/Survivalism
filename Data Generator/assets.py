from typing import Any

import mcresources.block_states as block_states
import mcresources.loot_tables as loot_tables
import mcresources.utils as utils
import json
import os

from mcresources import ResourceManager

from definitions import *
from lang import *

Path = NamedTuple('sub_path', sub=str)

CORE_PATHS: Dict[str, Path] = {
	'main': Path('../src/main/resources/data/survivalism/data'),
	'vegetation': Path('/vegetation/'),
	'food': Path('/food/'),
	'seeds': Path('/seeds/')
}

VEG_PATHS: Dict[str, Path] = {
	'herb': Path('/vegetation/herb/'),
	'flower': Path('/vegetation/flower/'),
	'bush': Path('/vegetation/bush/'),
	'grass': Path('/vegetation/grass/')
}

def generate(rm: ResourceManager):

	main_data_dir = CORE_PATHS['main'].sub

	for core_path in CORE_PATHS.keys():
		full_path = ''
		if core_path == 'main':
			full_path = CORE_PATHS[core_path].sub
		else:
			full_path = main_data_dir+CORE_PATHS[core_path].sub	
		try:
			os.mkdir(full_path, 0o777)
		except OSError:
			print ("Creation of the directory %s failed or already exists" % full_path)
		else:
			print("Successfully created the directory %s" % full_path)

	for sub_path in VEG_PATHS.keys():
		full_path = main_data_dir+VEG_PATHS[sub_path].sub
		try:
			os.mkdir(full_path, 0o777)
		except OSError:
			print ("Creation of the directory %s failed or already exists" % full_path)
		else:
			print("Successfully created the directory %s" % full_path)


	for veg in VEG.keys():

		# Initialize data file
		data = {}

		if VEG[veg].category == 'herb':
			block = rm.blockstate(('survivalism:veg/herb/%s' % veg), variants={'': [{'model': 'survivalism:veg/herb/%s%d' % (veg, i+1)} for i in range(VEG[veg].variants)]}, use_default_model=False)
			item = rm.item_model(('herb', veg))
			block.with_lang(lang('%s', veg))
			item.with_lang(lang('%s', veg))
			block.with_block_loot({
				'entries': 'survivalism/veg/herb/%s' % veg,
				'functions': [
					loot_tables.set_count(1, 2)
				]
			})
			for var in range(VEG[veg].variants):
				rm.block_model(('herb', veg+str(var+1)), textures={'texture': 'survivalism:block/veg/herb/%s%d' % (veg, var+1)}, parent='minecraft:block/cross')

			idx = 0
			for field in VEG[veg]._fields:
				data[field] = VEG[veg][idx]
				idx += 1

			with open('%s%s%s.json' % (main_data_dir, VEG_PATHS[VEG[veg].category].sub, veg), 'w') as output:
				json.dump(data, output)