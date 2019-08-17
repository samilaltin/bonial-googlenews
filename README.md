# alt yapı geliştirmeleri:

    (package : di)
-ActivityBuilder :  dagger'ın activity'i oluşturması için açılan yüm activityleri modulleri ile aynı formatta yazılmalıdır.
-AppComponent : açılan yeni modülü buraya modul olarak eklenmeli
-AppModule : Uygulamanın herhangi bir yerinde bir kere oluşturulup devam etmesi gereken clasları burada  aynı formatta oluşturabilirsiniz

    (package: api)
-ApiHelper : retrofit callbacklerinin oluşturulduğu  inteface
-AppApiHelper : interactor'ün bağlantı yaptığı classdır her servis sogusu için ayrı metod açılarak devam edilir aynı formatta yazılabilir. ilerde daha fazla özelleştirilebilir.
-ApiDisposable :rxjavanın responsu handle edip Disposable return etmesi için geliştirlmiş classtır.
-ApiError : Handle da sorun oluşursa parse ,connetion gibi hatalırı barındıran class


    (örnek uı package : spalash)
-interactor: bu view  için gereken servis metodlarını burası üzerinden haberleştirmemiz gerekmekte  AppApiHelper daki metodu kullanabilmeniz için ilk olarak burada tanımlamalısınız.
-presneter :interactor'e bağlanarak servis isteğinde bulunabilirsiniz. classa constructor da interactor  geliyor oradan servise çıkabilirsini
-view :
-....ActivityModule : bu sayfada kullanılacak interactor presneter gibi  classları daggera bildirdiğimiz modul



