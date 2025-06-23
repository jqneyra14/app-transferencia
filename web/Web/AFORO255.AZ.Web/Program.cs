using AFORO255.AZ.Web.Account.Implementations;
using AFORO255.AZ.Web.Account.Interfaces;
using AFORO255.AZ.Web.Proxy;
using AFORO255.AZ.Web.Services.Transaction.Implementations;
using AFORO255.AZ.Web.Services.Transaction.Interfaces;
using Microsoft.AspNetCore.Authentication.Cookies;
using MS.AFORO255.Web.Service.Auth.Implementations;
using MS.AFORO255.Web.Service.Auth.Interfaces;
using MS.AFORO255.Web.Service.History.Implementations;
using MS.AFORO255.Web.Service.History.Interfaces;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

builder.Services.AddSingleton<IHttpClient, CustomHttpClient>();

builder.Services.Configure<CookiePolicyOptions>(options =>
{
    options.CheckConsentNeeded = context => true;
    options.MinimumSameSitePolicy = SameSiteMode.None;
});

//builder.Services.AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
//       .AddCookie(CookieAuthenticationDefaults.AuthenticationScheme, options =>
//       {
//           options.Cookie.Name = "_auth";
//           options.LoginPath = new PathString("/Security/Login");
//           options.LogoutPath = new PathString("/Security/Login");
//           options.Cookie.HttpOnly = true;
//           options.ExpireTimeSpan = TimeSpan.FromDays(1);
//           options.AccessDeniedPath = new PathString("/Security/Login");
//       });


builder.Services.AddScoped<ISecurityService, SecurityService>();
builder.Services.AddScoped<IAccountService, AccountService>();
builder.Services.AddScoped<IMovementService, MovementService>();
builder.Services.AddScoped<ITransactionService, TransactionService>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
}
app.UseStaticFiles();

app.UseRouting();
//app.UseAuthentication();
//app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
