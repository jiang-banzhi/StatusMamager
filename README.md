
[![](https://jitpack.io/v/jiang-banzhi/StatusMamager.svg)](https://jitpack.io/#jiang-banzhi/StatusMamager)

### 使用
    添加依赖
    
    [![](https://jitpack.io/v/jiang-banzhi/StatusMamager.svg)](https://jitpack.io/#jiang-banzhi/StatusMamager)
    
    allprojects {
    	repositories {
    		...
    		maven { url 'https://jitpack.io' }
    	}
    }
    
    dependencies {
            implementation 'com.github.jiang-banzhi:StatusMamager:1.0.4'
    }
    
     helper = new StatusManager.Builder(this, recyclerView).build();
     helper = new StatusManager.Builder(this)
                .setContentView(recyclerView)
                .setEmptyView(new EmptyView(this,"空数据"))
                .setErrorView(new EmptyView(this,"错误布局"))
                .build();
     helper.init(this);    
     
     switch (item.getItemId()) {
         case R.id.menu_empty:
              helper.showEmpty();//显示空数据页面
              break;
         case R.id.menu_error:
              helper.showError();//显示错误页面
              break;
         case R.id.menu_loading:
              helper.showLoading();//显示加载页面
              break;
         case R.id.menu_content:
              helper.showContent();//显示内容
              break;
         
     }
#### 说明
    为了统一管理空布局 错误布局，设置空布局建议新建类继承ViewLoader，如:
    public class EmptyLoad extends ViewLoader {
         
        public EmptyView(Context context) {
            super(context);
    
        }
    
        @Override
        protected View createView() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(16f);
            textView.setText("自定义"+textView+"界面");
            frameLayout.addView(textView);
            return frameLayout;
    //        return LayoutInflater.from(getContext()).inflate(R.layout.empty,  null);
        }
    }
    使用:
    new ELoad.Builder(this, recyclerView).setEmptyView(new EmptyLoad(content)).build();
    如部分界面需要特殊处理，可直接传入view;
    
     helper.init()方法有两种实现，一种是通过回调实现点击空布局、异常布局操作，
         helper.init(new OnLayoutClickListener() {
                 @Override
                 public void onEmptyViewClick() {
                     //TODO 加载数据
                 }
     
                 @Override
                 public void onErrorViewClick() {
                    //TODO 加载数据
                 }
             });
      
     另一种是通过反射，自动调用加载数据方法，需要添加 @ViewClick(LoadType.BOTH)注解，init方法需要传入当前类；
    
     helper.init(this);
       
    @ViewClick(LoadType.BOTH)
    public void click() {
        if (type == 1) {
            Toast.makeText(this, "点击空布局", Toast.LENGTH_SHORT).show();
            helper.showEmpty();
        } else if (type == 2) {
            Toast.makeText(this, "点击错误布局", Toast.LENGTH_SHORT).show();
        }
    } 
    具体使用请查看sample代码。
     
