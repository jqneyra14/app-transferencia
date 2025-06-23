using AFORO255.AZ.Web.DTOs.Security;
using AFORO255.AZ.Web.Extensions;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using MS.AFORO255.Web.Service.Auth.Interfaces;
using System.Security.Claims;

namespace AFORO255.AZ.Web.Controllers
{
    public class SecurityController : Controller
    {
        private readonly ISecurityService _securityService;

        public SecurityController(ISecurityService securityService)
        {
            _securityService = securityService;
        }

        [AllowAnonymous]
        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(string username, string password)
        {
            SecurityDTORequest securityDTORequest = new SecurityDTORequest()
            {
                Email = username,
                Password = password
            };
            //SecurityDTOResponse authDTOResponse = await _securityService.Login(securityDTORequest);

            //if (string.IsNullOrEmpty(authDTOResponse.Token))
            //    return View();

            //var claims = new List<Claim>();
            //claims.Add(new Claim(CustomClaimTypes.Token, authDTOResponse.Token));
            //ClaimsIdentity userIdentity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);
            //ClaimsPrincipal principal = new ClaimsPrincipal(userIdentity);
            //await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal);
            return RedirectToAction("Index", "Home");
        }

        public async Task<IActionResult> Logout()
        {
            await HttpContext.SignOutAsync();
            return RedirectToAction("Index", "Home");
        }
    }
}
