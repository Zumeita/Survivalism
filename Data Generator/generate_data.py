import argparse

from mcresources import ResourceManager, clean_generated_resources

import assets
from definitions import *
from lang import *

def main():
    parser = argparse.ArgumentParser(description='Generate resources for Survivalism')
    parser.add_argument('--clean', action='store_true', dest='clean', help='Clean all auto generated resources')
    parser.add_argument('--hotswap', action='store_true', dest='hotswap', help='Also generate resources into the /out/production/resources directory, creates an asset hotswap')
    args = parser.parse_args()

    rm = ResourceManager('survivalism', resource_dir='../src/main/resources')
    if args.clean:
        # Stupid windows file locking errors.
        for tries in range(1, 1 + 3):
            try:
                clean_generated_resources('/'.join(rm.resource_dir))
                print('Clean Success')
                return
            except Exception:
                print('Failed, retrying (%d / 3)' % tries)
        print('Clean Aborted')
        return

    generate_all(rm)
    print('New = %d, Modified = %d, Unchanged = %d, Errors = %d' % (rm.new_files, rm.modified_files, rm.unchanged_files, rm.error_files))

    if args.hotswap:
        # Generate into the /out/production/resources folder, which is used when build + run with Intellij
        rm = ResourceManager('tfc', resource_dir='../out/production/resources')
        generate_all(rm)
        print('Hotswap Finished')


def generate_all(rm: ResourceManager):
    
    # generic lang first, dynamic lang included in assets.py
    rm.lang(STANDARD_LANG)

    # assets/data
    assets.generate(rm)

    rm.flush()




if __name__ == '__main__':
    main()