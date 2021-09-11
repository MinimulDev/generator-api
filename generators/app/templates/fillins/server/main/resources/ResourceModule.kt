package <%= serverPackageName %>.resources

import org.koin.dsl.module

val resourceModule = module {
    single { RootResource() }
}